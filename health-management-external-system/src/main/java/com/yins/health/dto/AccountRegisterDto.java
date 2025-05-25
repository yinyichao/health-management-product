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
    private Long id;
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
    @Schema(description = "手机号", required = true)
    private String phone;

/*    *//**
     * 头像
     *//*
    @Schema(description = "头像", required = true)
    private String avatarUrl;*/


    @Schema(description = "租户ID", required = true)
    private  String tenantId;


    @Schema(description = "角色1管理员2白名单3陌生人", required = true)
    private  String role;

    @Schema(description = "用户部门", required = true)
    private  Long departmentId;

}