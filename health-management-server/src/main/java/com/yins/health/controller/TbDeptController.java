package com.yins.health.controller;

import com.yins.health.service.TbDeptService;
import com.yins.health.util.AppResult;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门表;(TbDept)表控制层
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
@Api(tags = "部门表;API")
@RestController
@RequestMapping("/api/dept/v1")
public class TbDeptController {

    @Autowired
    private TbDeptService tbDeptService;
    /**
     * 注册接口
     */
    @GetMapping("tree")
    @Operation(summary = "获取用户树", description = "获取用户树")
    public AppResult tree() {
        return AppResult.successResult(tbDeptService.getRootDeptWithUsers());
    }

    @GetMapping("userByName")
    @Operation(summary = "根据用户名模糊查询", description = "根据用户名模糊查询")
    public AppResult userByName(String name) {
        return AppResult.successResult(tbDeptService.userByName(name));
    }

    @GetMapping("deptByName")
    @Operation(summary = "根据部门名模糊查询", description = "根据部门名模糊查询")
    public AppResult deptByName(String name) {
        return AppResult.successResult(tbDeptService.deptByName(name));
    }

}

