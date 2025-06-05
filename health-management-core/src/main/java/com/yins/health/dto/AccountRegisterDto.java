package com.yins.health.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @Description
 * @Author Darius
 * @Version 1.0
 **/
@Schema(description = "用户注册请求体")
@Data
public class AccountRegisterDto {

    @Schema(description = "用户id")
    private Integer id;
    /**
     * 用户名
     */
    @Schema(description = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码", required = true)
    private String password;

    /**
     * 手机号
     */
    @Schema(description = "0、没有管理权限，1、有管理权限", required = true)
    private Integer isAdmin;


}