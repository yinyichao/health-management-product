package com.yins.health.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @Description
 * @Author Darius
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    @Value("endpoint")
    private String endpoint;

    @Value("access-key")
    private String accessKey;

    @Value("access-secret")
    private String accessSecret;

    @Value("bucket-name")
    private String bucketName;

    @Value("bucket-name-public")
    private String bucketNamePublic;

    @Value("avatar-bucket-name")
    private String avatarBucketName;

    @Value("ai-bucket-name")
    private String aiBucketName;



    //预签名的URL过期时间 ms 毫秒
    private Long preSignUrlExpireTime = 60 * 60 * 1000L;



    public static final String DEFAULT_TEMPLATE_PREFIX = "template";

    public static final String DEFAULT_PPT_PREFIX = "ai_ppt";


//
//    @Bean
//    public MinioClient getMinIoClient(){
//        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, accessSecret).build();
//    }


}