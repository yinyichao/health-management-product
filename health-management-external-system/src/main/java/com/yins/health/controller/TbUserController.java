package com.yins.health.controller;

import com.yins.health.dto.AccountDto;
import com.yins.health.dto.AccountRegisterDto;
import com.yins.health.dto.LoginDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbUserService;
import com.yins.health.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yins.health.util.AppResult;

/**
 * 用户表;(TbUser)表控制层
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
@Api(tags = "用户表;API")
@RestController
@RequestMapping("/api/account/v1")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    /**
     * 注册接口
     */
    @PostMapping("register")
    @Operation(summary = "用户注册", description = "用户注册接口")
    public AppResult register(@RequestBody AccountRegisterDto req) {
        tbUserService.register(req);
        return AppResult.emptyResult();
    }

    /**
     * 登录模块
     */
    @PostMapping("login")
    @Operation(summary = "用户登录", description = "用户名和密码登录")
    public AppResult login(@RequestBody LoginDto loginDto) {
        LoginVo login = tbUserService.login(loginDto.getPayLoad());
        return AppResult.successResult(login);
    }
    /**
     * 注册接口
     */
    @PostMapping("logout")
    @Operation(summary = "退出登录", description = "退出登录接口")
    public AppResult  logout(HttpServletRequest request) {
        tbUserService.logout(request);
        return AppResult.successResult("退出登录成功");
    }
    /**
     * 获取用户详情接口
     */
    @GetMapping("detail")
    @Operation(summary = "获取用户详情", description = "获取当前登录用户详情接口")
    public AppResult detail() {
        AccountDto accountDTO = tbUserService.queryDetail(LoginInterceptor.threadLocal.get().getId());
        return AppResult.successResult(accountDTO);
    }
    /**
     * 获取用户详情接口
     */
    @PostMapping("update")
    @Operation(summary = "修改用户信息")
    public AppResult update(@RequestBody AccountRegisterDto req) {
        tbUserService.update(req);
        return AppResult.emptyResult();
    }

    @GetMapping("authentication")
    @Operation(summary = "企微鉴权", description = "企微鉴权-获取当前登录用户详情接口")
    public AppResult authentication(String code){
        return AppResult.successResult(tbUserService.authentication(code));
    }
    @GetMapping("weixin")
    @Operation(summary = "企微同步部门和用户数据", description = "企微同步部门和用户数据接口")
    public AppResult weixin() throws Exception {
        tbUserService.weixin();
        return AppResult.successResult("");
    }
    /**
     * <h2>获取JS-SDK</h2>
     *
     */
    @GetMapping("jssdk")
    @Operation(summary = "jssdk", description = "企微-jssdk")
    public AppResult obtainConfigParam(){
        return  AppResult.successResult(tbUserService.obtainConfigParam());
    }

}

