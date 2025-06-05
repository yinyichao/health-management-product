package com.yins.health.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.conf.AccountConfig;
import com.yins.health.conf.JwtUtil;
import com.yins.health.conf.RedisKey;
import com.yins.health.conf.WeixinConfig;
import com.yins.health.constant.BizCodeEnum;
import com.yins.health.dao.*;
import com.yins.health.dto.AccountDto;
import com.yins.health.dto.AccountRegisterDto;
import com.yins.health.entity.*;
import com.yins.health.exception.BizException;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbUserService;
import com.yins.health.util.AesUtil;
import com.yins.health.util.CommonUtil;
import com.yins.health.util.HttpUtils;
import com.yins.health.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户表;(TbUser)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
@Service("tbUserService")
@Slf4j
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUser> implements TbUserService {

    @Autowired
    private RedisKey redisKey;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserOnlineLogDao userOnlineLogDao;
    @Autowired
    private TbDeptDao tbDeptDao;
    @Autowired
    private TbUserDeptDao tbUserDeptDao;
    @Autowired
    private WeixinConfig weixinConfig;
    @Value( "${login.salt}")
    private String salt;

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
/*        List<TbUser> accountDOList = baseMapper.selectList(new QueryWrapper<TbUser>().eq("username", req.getUsername()));
        if (!accountDOList.isEmpty()) {
            throw new BizException(BizCodeEnum.ACCOUNT_REPEAT.getCode(),BizCodeEnum.ACCOUNT_REPEAT.getMessage());
        }*/

        TbUser accountDO = CommonUtil.convert(req, TbUser.class);

        //加密密码
        String digestAsHex = DigestUtils.md5DigestAsHex((AccountConfig.ACCOUNT_SALT + req.getPassword()).getBytes());
        assert accountDO != null;
        accountDO.setPassword(digestAsHex);
        // 生成一个随机UUID
        UUID uuid = UUID.randomUUID();
        // 将UUID转换为字符串
        accountDO.setId(uuid.toString());
        baseMapper.insert(accountDO);
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
        TbUser accountDO = baseMapper.selectOne(new QueryWrapper<TbUser>()
                .eq("username", username)
                .eq("password", password));
        //数据库表字段没有区分大小写
        if (accountDO == null||!accountDO.getUsername().equals(username)) {
            log.error("用户不存在或密码错误，用户名: {}", username);
            throw new BizException(BizCodeEnum.ACCOUNT_PWD_ERROR.getCode(), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
        }

        AccountDto accountDTO = CommonUtil.convert(accountDO, AccountDto.class);
        assert accountDTO != null;
        recordLogin(accountDTO.getId(),null,null);

        // set、
        return loginSuccessTask(accountDTO);
    }
    public void recordLogin(String accountId, String ip, String userAgent) {
        UserOnlineLog log = new UserOnlineLog();
        log.setAccountId(accountId);
        log.setLoginTime(LocalDateTime.now());
        log.setIpAddress(ip);
        log.setUserAgent(userAgent);
        userOnlineLogDao.insert(log);
    }
    public LoginVo loginSuccessTask( AccountDto accountDTO ) {
        String sessionKey = redisKey.token_key_pre+accountDTO.getId();
        String token = JwtUtil.geneLoginJWT(accountDTO);
        LoginVo loginVo = new LoginVo(accountDTO, token);
        stringRedisTemplate.opsForValue().set(sessionKey,token,1000 * 60 * 60 * 24 * 7L, TimeUnit.SECONDS);
        return loginVo;
    }
    @Override
    public void logout(HttpServletRequest request) {
        AccountDto accountDTO = LoginInterceptor.threadLocal.get();
        String tokenKey = redisKey.token_key_pre+ LoginInterceptor.threadLocal.get().getId();
        stringRedisTemplate.opsForValue().getAndDelete(tokenKey);
        log.info("用户 [{}] 已退出登录", accountDTO.getUsername());
        recordLogout(accountDTO.getId());
    }
    public void recordLogout(String accountId) {
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
        TbUser one = this.getOne(new LambdaQueryWrapper<>(TbUser.class).eq(TbUser::getId, req.getId()));
        if (ObjectUtil.isEmpty(one)) {
            throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER.getCode(), BizCodeEnum.ACCOUNT_UNREGISTER.getMessage());
        }
        one.setUsername(req.getUsername());
        one.setPassword(DigestUtils.md5DigestAsHex((AccountConfig.ACCOUNT_SALT + req.getPassword()).getBytes()));
        this.updateById(one);
    }

    @Override
    public LoginVo authentication(String code){
        String tokenKey = redisKey.access_token;
        String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);
        if (redisToken == null) {
            String url = weixinConfig.getUrl() + "?corpid=" + weixinConfig.getCorpid() + "&corpsecret=" + weixinConfig.getCorpsecret();
            String res = null;
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
        String url = weixinConfig.getUserinfoUrl() + "?access_token="+redisToken+"&code=" + code;
        String res = null;
        try {
            res = HttpUtils.sendGetString(url);
        } catch (Exception e) {
            throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
        }
        JSONObject json = JSONUtil.parseObj(res);
        String user_ticket = json.getStr("user_ticket");
        if(redisToken == null) {
            throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
        }
        TbUser tbUser = baseMapper.selectById(user_ticket);
        if(tbUser == null) {
            throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
        }

        AccountDto accountDTO = CommonUtil.convert(tbUser, AccountDto.class);
        assert accountDTO != null;
        recordLogin(accountDTO.getId(),null,null);

        return loginSuccessTask(accountDTO);
    }

    @Override
    public void weixin() throws Exception {
        String tokenKey = redisKey.access_token;
        String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);
        if (redisToken == null) {
            String url = weixinConfig.getUrl() + "?corpid=" + weixinConfig.getCorpid() + "&corpsecret=" + weixinConfig.getCorpsecret();
            String res = HttpUtils.sendGetString(url);
            JSONObject json = JSONUtil.parseObj(res);
            redisToken = json.getStr("access_token");
            if(redisToken == null) {
                throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
            }else{
                stringRedisTemplate.opsForValue().set(tokenKey,redisToken,1000 * 7, TimeUnit.SECONDS);
            }
        }
        String url = weixinConfig.getDeptUrl() + "?access_token=" + redisToken;
        String res = HttpUtils.sendGetString(url);
        JSONObject json = JSONUtil.parseObj(res);
        List<TbDept> deptList = JSONUtil.toList(json.getJSONArray("department"), TbDept.class);
        tbDeptDao.delete(null);
        baseMapper.delete(new LambdaQueryWrapper<TbUser>().eq(TbUser::getIsAdmin,0));
        tbUserDeptDao.delete(null);
        TbUser tbUser;
        TbUserDept tbUserDept;
        for (TbDept tbDept : deptList) {
            tbDeptDao.insert(tbDept);
            url = weixinConfig.getUserUrl() + "?access_token=" + redisToken + "&department_id="+tbDept.getId();
            res = HttpUtils.sendGetString(url);
            json = JSONUtil.parseObj(res);
            JSONArray userlist = json.getJSONArray("userlist");
            for (int i = 0; i < userlist.size(); i++) {
                tbUser = new TbUser();
                tbUserDept = new TbUserDept();
                JSONObject jsonObject = userlist.getJSONObject(i);
                String name = jsonObject.getStr("name");
                String userid = jsonObject.getStr("userid");
                tbUser.setUsername(name);
                tbUser.setId(userid);
                tbUser.setIsAdmin(0);
                tbUserDept.setUserId(tbUser.getId());
                tbUserDept.setDeptId(tbDept.getId());
                baseMapper.insert(tbUser);
                tbUserDeptDao.insert(tbUserDept);
            }
        }
    }

    @Override
    public AccountDto queryDetail(String id) {
        //账号详情
        TbUser accountDO = baseMapper.selectById(id);
        return CommonUtil.convert(accountDO, AccountDto.class);
    }
}

