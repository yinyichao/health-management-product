package com.yins.health.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.component.StoreEngine;
import com.yins.health.conf.AccountConfig;
import com.yins.health.conf.JwtUtil;
import com.yins.health.conf.MinioConfig;
import com.yins.health.conf.RedisKey;
import com.yins.health.constant.BizCodeEnum;
import com.yins.health.dao.*;
import com.yins.health.dto.AccountDto;
import com.yins.health.dto.AccountRegisterDto;
import com.yins.health.dto.FolderCreateDto;
import com.yins.health.dto.StorageDTO;
import com.yins.health.entity.Account;
import com.yins.health.entity.AccountFile;
import com.yins.health.entity.Storage;
import com.yins.health.entity.UserOnlineLog;
import com.yins.health.exception.BizException;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.AccountFileService;
import com.yins.health.service.AccountService;
import com.yins.health.util.AesUtil;
import com.yins.health.util.CommonUtil;
import com.yins.health.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Darius
 * @Version 1.0
 **/
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {

    @Autowired
    private UserOnlineLogDao userOnlineLogDao;

    @Autowired
    private StoreEngine fileStoreEngine;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private AccountFileService accountFileService;
    @Autowired
    private StorageDao storageDao;
    @Autowired
    private RedisKey redisKey;

    @Autowired
    private AccountFileDao accountFileDao;
    @Value( "${login.salt}")
    private String salt;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AccountRoleDao accountRoleDao;
    @Autowired
    private AccountDepartmentDao accountDepartmentDao;

    /**
     * 1、查询手机号是否重复
     * 2、加密密码
     * 3、插入数据库
     * 4、其他相关初始化操作
     *
     * @param req
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(AccountRegisterDto req) {

        //1、查询手机号和用户名是否重复
        List<Account> accountDOList = baseMapper.selectList(new QueryWrapper<Account>().eq("phone", req.getPhone()).eq("username", req.getUsername()));
        if (!accountDOList.isEmpty()) {
            throw new BizException(BizCodeEnum.ACCOUNT_REPEAT.getCode(),BizCodeEnum.ACCOUNT_REPEAT.getMessage());
        }

        Account accountDO = CommonUtil.convert(req, Account.class);

        //加密密码
        String digestAsHex = DigestUtils.md5DigestAsHex((AccountConfig.ACCOUNT_SALT + req.getPassword()).getBytes());
        accountDO.setPassword(digestAsHex);
        accountDO.setId(null);
        baseMapper.insert(accountDO);
        accountRoleDao.insertAccountRole(accountDO.getId(), Long.valueOf(accountDO.getRole()));
        accountDepartmentDao.insertAccountDepartment(accountDO.getId(), req.getDepartmentId());

        //初始化根目录
        FolderCreateDto createRootFolderReq = FolderCreateDto.builder().accountId(accountDO.getId()).parentId(AccountConfig.ROOT_PARENT_ID).folderName(AccountConfig.ROOT_FOLDER_NAME).build();

        accountFileService.createFolder(createRootFolderReq);
    }

    @Override
    public String uploadAvatar(MultipartFile file) {
        String filename = CommonUtil.getFilePath(file.getOriginalFilename());
        fileStoreEngine.upload(minioConfig.getAvatarBucketName(), filename, file);
        return minioConfig.getEndpoint() + "/" + minioConfig.getAvatarBucketName() + "/" + filename;
    }

    @Override
    public LoginVo login(String payLoad) {
        String username, password;
        //处理密码
        try {
            log.info("开始解密登录参数，payLoad: {}", payLoad);
            if (StringUtils.isBlank(payLoad)) {
                log.error("登录参数为空");
                throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR.getCode(), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
            }

            String jsonStr = AesUtil.aesDecrypt(payLoad, salt);
            log.info("解密后的JSON字符串: {}", jsonStr);

            if (StringUtils.isBlank(jsonStr)) {
                log.error("解密后的JSON字符串为空");
                throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR.getCode(), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
            }
            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            username = jsonObject.get("username", String.class);
            password = jsonObject.get("password", String.class);
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                log.error("用户名或密码为空，username: {}, password: {}", username, password);
                throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR.getCode(), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
            }
            log.info("解析后的用户名: {}, 密码: {}", username, password);
        } catch (IllegalArgumentException e) {
            log.error("登录参数解密失败: {}", e.getMessage());
            throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR.getCode(), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
        } catch (Exception e) {
            log.error("登录过程发生异常: {}", e.getMessage(), e);
            throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR.getCode(), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
        }

        // 加密密码
        password = DigestUtils.md5DigestAsHex((AccountConfig.ACCOUNT_SALT + password).getBytes());
        log.info("加密后的密码: {}", password);

        // 查询用户
        Account accountDO = baseMapper.selectOne(new QueryWrapper<Account>()
                .eq("username", username)
                .eq("password", password));
        //数据库表字段没有区分大小写
        if (accountDO == null||!accountDO.getUsername().equals(username)) {
            log.error("用户不存在或密码错误，用户名: {}", username);
            throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR.getCode(), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
        }

        AccountDto accountDTO = CommonUtil.convert(accountDO, AccountDto.class);
        recordLogin(accountDTO.getId(),null,null, Long.valueOf(accountDO.getTenantId()));

        // set、
        return loginSuccessTask(accountDTO);
    }
    public LoginVo loginSuccessTask( AccountDto accountDTO ) {
        String sessionKey = redisKey.token_key_pre+accountDTO.getId();
        String token = JwtUtil.geneLoginJWT(accountDTO);
        LoginVo loginVo = new LoginVo(accountDTO, token);
        stringRedisTemplate.opsForValue().set(sessionKey,token,1000 * 60 * 60 * 24 * 7L, TimeUnit.SECONDS);
        return loginVo;
    }

    @Override
    public AccountDto queryDetail(Long id) {

        //账号详情
        Account accountDO = baseMapper.selectById(id);
        AccountDto accountDTO = CommonUtil.convert(accountDO, AccountDto.class);
        //获取存储信息
        Storage storageDO = storageDao.selectOne(new QueryWrapper<Storage>().eq("account_id", id));
        accountDTO.setStorageDTO(CommonUtil.convert(storageDO, StorageDTO.class));

        //获取文件信息
        AccountFile accountFileDO = accountFileDao.selectOne(new QueryWrapper<AccountFile>()
                .eq("account_id", id).eq("parent_id", AccountConfig.ROOT_PARENT_ID));
        accountDTO.setRootFileId(accountFileDO.getId());
        accountDTO.setRootFileName(accountFileDO.getFileName());

        return accountDTO;
    }

    @Override
    public void logout(HttpServletRequest request) {
        AccountDto accountDTO = LoginInterceptor.threadLocal.get();
        String tokenKey = redisKey.token_key_pre+ LoginInterceptor.threadLocal.get().getId();
        stringRedisTemplate.opsForValue().getAndDelete(tokenKey);
        log.info("用户 [{}] 已退出登录", accountDTO.getUsername());
        recordLogout(accountDTO.getId());
    }

    public void recordLogin(Long accountId, String ip, String userAgent, Long tenantId) {
        UserOnlineLog log = new UserOnlineLog();
        log.setAccountId(accountId);
        log.setLoginTime(LocalDateTime.now());
        log.setIpAddress(ip);
        log.setUserAgent(userAgent);
        log.setTenantId(tenantId);
        userOnlineLogDao.insert(log);
    }
    public void recordLogout(Long accountId) {
        LambdaQueryWrapper<UserOnlineLog> query = Wrappers.lambdaQuery(UserOnlineLog.class)
                .eq(UserOnlineLog::getAccountId, accountId)
                .isNull(UserOnlineLog::getLogoutTime)
                .orderByDesc(UserOnlineLog::getLoginTime)
                .last("LIMIT 1");

        UserOnlineLog log = userOnlineLogDao.selectOne(query);

        if (log != null) {
            LocalDateTime now = LocalDateTime.now();
            log.setLogoutTime(now);
            log.setOnlineSecs(java.time.Duration.between(log.getLoginTime(), now).getSeconds());
            userOnlineLogDao.updateById(log);
        }
    }

    @Override
    public void update(AccountRegisterDto req) {
        Account one = this.getOne(new LambdaQueryWrapper<>(Account.class).eq(Account::getId, req.getId()));
        if (ObjectUtil.isEmpty(one)) {
            throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER.getCode(), BizCodeEnum.ACCOUNT_UNREGISTER.getMessage());
        }
        one.setUsername(req.getUsername());
        one.setPassword(DigestUtils.md5DigestAsHex((AccountConfig.ACCOUNT_SALT + req.getPassword()).getBytes()));
        this.updateById(one);
    }
}