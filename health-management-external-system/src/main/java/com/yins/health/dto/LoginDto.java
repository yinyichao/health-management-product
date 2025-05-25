package com.yins.health.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/*
 *TODO
 *@Author Mister
 *@Date 2025/3/27 13:49
 */
@Data
@Schema(description = "登录请求对象")
public class LoginDto {
    @Schema(description = "登录请求体")
    private String payLoad;
}

