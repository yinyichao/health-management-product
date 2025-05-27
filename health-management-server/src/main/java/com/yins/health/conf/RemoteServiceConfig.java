package com.yins.health.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author DongXin
 * @date 2025/4/10 13:30
 */
@Data
@Component
@ConfigurationProperties(prefix = "remote")
public class RemoteServiceConfig {

    @Value("python-url")
    private String pythonUrl;

    @Value("app-key")
    private String appKey;

    @Value("special-id")
    private String specialId;


}
