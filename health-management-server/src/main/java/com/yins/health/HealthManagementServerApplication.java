package com.yins.health;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling  // ✅ 开启定时任务
@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan({"com.yins.health.dao"})
public class HealthManagementServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthManagementServerApplication.class, args);
    }

}
