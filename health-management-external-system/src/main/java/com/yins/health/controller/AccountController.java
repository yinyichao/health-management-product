package com.yins.health.controller;

import com.yins.health.dto.AccountDto;
import com.yins.health.dto.AccountRegisterDto;
import com.yins.health.dto.LoginDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.AccountService;
import com.yins.health.util.AppResult;
import com.yins.health.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 账户管理控制器
 * @Author Darius
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/account/v1")
@Tag(name = "账户管理接口", description = "账户相关的操作接口")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 注册接口
     */
    @PostMapping("register")
    @Operation(summary = "用户注册", description = "用户注册接口")
    public AppResult register(@RequestBody AccountRegisterDto req) {
        accountService.register(req);
        return AppResult.emptyResult();
    }

    /**
     * 头像上传接口
     */
    @PostMapping("upload_avatar")
    @Operation(summary = "上传头像", description = "上传用户头像接口")
    public AppResult uploadAvatar(@RequestParam("file") MultipartFile file) {
        String url = accountService.uploadAvatar(file);
        return AppResult.successResult(url);
    }

    /**
     * 登录模块
     */
    @PostMapping("login")
    @Operation(summary = "用户登录", description = "用户名和密码登录")
    public AppResult login(@RequestBody LoginDto loginDto) {
        LoginVo login = accountService.login(loginDto.getPayLoad());
        return AppResult.successResult(login);
    }
    /**
     * 注册接口
     */
    @PostMapping("logout")
    @Operation(summary = "退出登录", description = "退出登录接口")
    public AppResult  logout(HttpServletRequest request) {
        accountService.logout(request);
        return AppResult.successResult("退出登录成功");
    }

    /**
     * 获取用户详情接口
     */
    @GetMapping("detail")
    @Operation(summary = "获取用户详情", description = "获取当前登录用户详情接口")
    public AppResult detail() {
        AccountDto accountDTO = accountService.queryDetail(LoginInterceptor.threadLocal.get().getId());
        return AppResult.successResult(accountDTO);
    }
    /**
     * 获取用户详情接口
     */
    @PostMapping("update")
    @Operation(summary = "修改用户信息")
    public AppResult update(@RequestBody AccountRegisterDto req) {
         accountService.update(req);
        return AppResult.emptyResult();
    }




}
