package com.yins.health.service.impl;

import com.yins.health.conf.RedisKey;
import com.yins.health.service.TbPhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 短信服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
@Service("tbPhoneService")
@Slf4j
public class TbPhoneServiceImpl implements TbPhoneService {

    @Autowired
    private RedisKey redisKey;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String sendSMS(String phoneNumber) {
        String result = "137854";
        String sessionKey = redisKey.phone_key_pre+phoneNumber;
        stringRedisTemplate.opsForValue().set(sessionKey,result,1000 * 60 * 5, TimeUnit.SECONDS);
        return result;
    }

    @Override
    public String verifySMS(String phone, String code) {
        String result = "";
        String tokenKey = redisKey.phone_key_pre + phone;
        String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);
        if (redisToken == null || !redisToken.equals(code)) {
            result = "{\"code\":201,\"msg\":\"校验失败\"}";
        }else{
            result = "{\"code\":200,\"msg\":\"校验成功\"}";
        }
        return result;
    }
}

