package com.yins.health.controller;


import com.yins.health.service.TbTaskService;
import com.yins.health.util.AppResult;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 移动端首页
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
@Api(tags = "移动端首页;API")
@RestController
@RequestMapping("/api/mobile/v1")
public class MobileController {
    @Autowired
    private TbTaskService tbTaskService;
    /**
     * 首页信息
     *
     * @return 首页信息
     */
    @GetMapping("/selectOne")
    @Operation(summary = "首页信息")
    public AppResult selectOne() {
        return AppResult.successResult(tbTaskService.seleteOne());
    }
}

