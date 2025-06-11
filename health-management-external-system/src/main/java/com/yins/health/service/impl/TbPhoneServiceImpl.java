package com.yins.health.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yins.health.conf.DuanxinConfig;
import com.yins.health.conf.RedisKey;
import com.yins.health.constant.BizCodeEnum;
import com.yins.health.exception.BizException;
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
    @Autowired
    private DuanxinConfig duanxinConfig;

    @Override
    public String sendSMS(String phoneNumber) {
        String redisToken = getString();
        String url = duanxinConfig.getSendUrl() + "?phoneNumber="+phoneNumber;
        HttpResponse response = HttpRequest.get(url)
                .header("Authorization", redisToken) // JSON类型
                //.body(jsonStr) // JSON数据
                .execute();
        return response.body();
    }

    private String getString() {
        String tokenKey = redisKey.phone_token_key_pre;
        String redisToken = stringRedisTemplate.opsForValue().get(tokenKey);
        if (redisToken == null) {
            String url = duanxinConfig.getUrl();
            String jsonStr = "{\"username\":\""+duanxinConfig.getUser()+"\",\"password\":\""+duanxinConfig.getPassword()+"\"}";
            HttpResponse response = HttpRequest.post(url)
                    .header("Content-Type", "application/json") // JSON类型
                    .body(jsonStr) // JSON数据
                    .execute();
            String res = response.body();
            JSONObject json = JSONUtil.parseObj(res);
            JSONObject jsonObject = json.getJSONObject("data");
            redisToken = jsonObject.getStr("access_token");
            if(redisToken == null) {
                throw new BizException(BizCodeEnum.ACCOUNT_UNREGISTER);
            }else{
                stringRedisTemplate.opsForValue().set(tokenKey,redisToken,100 * 7, TimeUnit.SECONDS);
            }
        }
        return redisToken;
    }

    @Override
    public String verifySMS(String phone, String code) {
        String url = duanxinConfig.getVerifyUrl() + "?phoneNumber="+phone + "&numberCode="+code;
        String redisToken = getString();
        HttpResponse response = HttpRequest.get(url).header("Authorization", redisToken).execute();
        String res = response.body();
        JSONObject json = JSONUtil.parseObj(res);
        String no = json.getStr("code");
        if("200".equals(no)) {
            String tokenKey = redisKey.phone_key_pre + phone;
            stringRedisTemplate.opsForValue().set(tokenKey,code,100 * 7, TimeUnit.SECONDS);
        }
        return res;
    }
}

