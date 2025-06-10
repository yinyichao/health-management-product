package com.yins.health.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeixinConfig {
    @Value("corpid")
    private String corpid;

    @Value("corpsecret")
    private String corpsecret;

    @Value("url")
    private String url;

    @Value("userinfoUrl")
    private String userinfoUrl;

    @Value("deptUrl")
    private String deptUrl;

    @Value("userUrl")
    private String userUrl;
    @Value("ticketUrl")
    private String ticketUrl;
}