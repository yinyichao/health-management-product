package com.yins.health.service;



/**
 * 短信服务接口
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
public interface TbPhoneService{
    String sendSMS(String phoneNumber);

    String verifySMS(String phone, String code);
}

