package com.yins.health.conf;

import org.springframework.context.annotation.Configuration;

/*
 *@Author Mister
 *@Date 2025/3/27 14:18
 */
@Configuration
public  class RedisKey {


    public final String  token_key_pre = "token:key:pre:";
    public final String  phone_key_pre = "phone:key:pre";
    public final String  phone_token_key_pre = "phone:token:key:pre";
    public final String  access_token = "access_token:";
    public final String  ticket_pre = "HLMCheckup:HhyWechat:JsApi:";
    public final String  weixin_ticket_pre = "weixin:ticket:key:pre:";
    public final String  weixin_access_token = "weixin:access_token:";

}

