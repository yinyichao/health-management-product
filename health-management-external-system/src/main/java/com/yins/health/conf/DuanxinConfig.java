package com.yins.health.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "duanxin")
public class DuanxinConfig {

    @Value("url")
    private String url;

    @Value("user")
    private String user;

    @Value("password")
    private String password;

    @Value("sendUrl")
    private String sendUrl;

    @Value("verifyUrl")
    private String verifyUrl;
}