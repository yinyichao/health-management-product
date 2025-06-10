package com.yins.health.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.component.StoreEngine;
import com.yins.health.conf.MinioConfig;
import com.yins.health.conf.RedisKey;
import com.yins.health.conf.WeixinConfig;
import com.yins.health.constant.BizCodeEnum;
import com.yins.health.dao.TbFileDao;
import com.yins.health.entity.TbFile;
import com.yins.health.exception.BizException;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbFileService;
import com.yins.health.util.CommonUtil;
import com.yins.health.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.TimeUnit;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件表;(TbFile)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-27 19:10:53
 */
@Service("tbFileService")
public class TbFileServiceImpl extends ServiceImpl<TbFileDao, TbFile> implements TbFileService {


    @Autowired
    private StoreEngine fileStoreEngine;

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private WeixinConfig weixinConfig;
    @Autowired
    private RedisKey redisKey;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String upload(MultipartFile file) {
        String objectKey = CommonUtil.getFilePath(file.getOriginalFilename());
        fileStoreEngine.upload(minioConfig.getHealthBucketName(), objectKey, file);
        String downloadUrl = minioConfig.getPre() + "/" + objectKey;
                //String downloadUrl = fileStoreEngine.getDownloadUrl(minioConfig.getHealthBucketName(), objectKey);
        //String downloadUrl = fileStoreEngine.getDownloadUrl(minioConfig.getHealthBucketName(),
        //        objectKey, minioConfig.getPreSignUrlExpireTime(), TimeUnit.MILLISECONDS);
        TbFile tbFile = new TbFile();
        tbFile.setObjectKey(downloadUrl);
        tbFile.setFileName(file.getOriginalFilename());
        if(LoginInterceptor.threadLocal.get() != null) {
            String userid = LoginInterceptor.threadLocal.get().getId();
            tbFile.setCreatedUser(userid);
        }
        baseMapper.insert(tbFile);
        return downloadUrl;
    }

    @Override
    public String upload(String mediaId) {
        String redisToken = getAccessToken();
        String url = weixinConfig.getMediaUrl() + "?access_token="+redisToken+"&media_id=" + mediaId;
        String objectKey = "";

        try {
            objectKey = downloadAndUploadToS3(url,minioConfig.getHealthBucketName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String downloadUrl = minioConfig.getPre() + "/" + objectKey;
        TbFile tbFile = new TbFile();
        tbFile.setObjectKey(downloadUrl);
        tbFile.setFileName(objectKey);
        if(LoginInterceptor.threadLocal.get() != null) {
            String userid = LoginInterceptor.threadLocal.get().getId();
            tbFile.setCreatedUser(userid);
        }
        baseMapper.insert(tbFile);
        return downloadUrl;
    }
    public String downloadAndUploadToS3(String fileUrl, String bucketName) throws Exception {
        // 下载文件
        ResponseEntity<Resource> response = restTemplate.getForEntity(fileUrl, Resource.class);
        // 1. 获取 Content-Disposition 头
        HttpHeaders headers = response.getHeaders();
        String contentDisposition = headers.getFirst(HttpHeaders.CONTENT_DISPOSITION);

        String filename = null;

        // 2. 使用正则提取文件名
        if (contentDisposition != null) {
            Matcher matcher = Pattern.compile("filename\\*?=([^;]+)").matcher(contentDisposition);
            if (matcher.find()) {
                filename = matcher.group(1).trim()
                        .replaceAll("UTF-8''", "")  // RFC 5987 格式
                        .replaceAll("\"", "");     // 去除引号
                System.out.println("文件名（来自响应头）: " + filename);
            }
        }
        String objectKey = CommonUtil.getFilePath(filename);
        InputStream inputStream = response.getBody().getInputStream();

        // 读取为 byte[]
        byte[] fileBytes = StreamUtils.copyToByteArray(inputStream);
        inputStream.close();

        // 上传到 S3
        fileStoreEngine.upload(bucketName, objectKey, fileBytes);
        System.out.println("上传成功：" + objectKey);
        return objectKey;
    }
    private String getAccessToken() {
        String tokenKey = redisKey.access_token;
        String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);
        if (redisToken == null) {
            String url = weixinConfig.getUrl() + "?corpid=" + weixinConfig.getCorpid() + "&corpsecret=" + weixinConfig.getCorpsecret();
            String res;
            try {
                res = HttpUtils.sendGetString(url);
            } catch (Exception e) {
                throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
            }
            JSONObject json = JSONUtil.parseObj(res);
            redisToken = json.getStr("access_token");
            if(redisToken == null) {
                throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
            }else{
                stringRedisTemplate.opsForValue().set(tokenKey,redisToken,1000 * 7, TimeUnit.SECONDS);
            }
        }
        System.out.println("-----------------------------"+redisToken);
        return redisToken;
    }
/*    @Override
    public String getDownloadUrl(String objectKey){
        // 当前文件
        String downloadUrl = fileStoreEngine.getDownloadUrl(minioConfig.getHealthBucketName(),
                objectKey, minioConfig.getPreSignUrlExpireTime(), TimeUnit.MILLISECONDS);
        return downloadUrl;
    }*/
}

