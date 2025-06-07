package com.yins.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.component.StoreEngine;
import com.yins.health.conf.MinioConfig;
import com.yins.health.dao.TbFileDao;
import com.yins.health.entity.TbFile;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbFileService;
import com.yins.health.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

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
        String userid = LoginInterceptor.threadLocal.get().getId();
        tbFile.setCreatedUser(userid);
        baseMapper.insert(tbFile);
        return downloadUrl;
    }
/*    @Override
    public String getDownloadUrl(String objectKey){
        // 当前文件
        String downloadUrl = fileStoreEngine.getDownloadUrl(minioConfig.getHealthBucketName(),
                objectKey, minioConfig.getPreSignUrlExpireTime(), TimeUnit.MILLISECONDS);
        return downloadUrl;
    }*/
}

