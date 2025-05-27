package com.yins.health.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


/*
 *TODO
 *@Author Mister
 *@Date 2025/3/27 16:17
 */
@Data
@Schema(description = "RABC分页对象")
public class RABCPageDto {
    @Schema(description = "页码", example = "1")
    private Long pageNum = 1L;
    @Schema(description = "每页数量", example = "10")
    private Long pageSize = 10L;
    @Schema(description = "租户ID", example = "1001")
    private List<Long> tenantId;
    @Schema(description = "部门ID", example = "2002")
    private List<Long> departmentId;
    @Schema(description = "用户名", example = "zhangsan")
    private String username;
    @Schema(description = "手机号", example = "13800000000")
    private String phone;
    @Schema(description = "用户角色", example = "1")
    private String role;
}

