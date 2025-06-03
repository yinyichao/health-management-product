package com.yins.health.controller;


import com.yins.health.service.TbPhoneService;
import com.yins.health.util.AppResult;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 短信接口控制层
 *
 * @author yinyichao
 * @since 2025-05-27 19:10:53
 */
@Api(tags = "短信接口;API")
@RestController
@RequestMapping("/api/phone/v1")
public class TbPhoneController {
    @Autowired
    private TbPhoneService tbPhoneService;

    @GetMapping("/sendSMS")
    @Operation(summary = "发短信接口")
    public AppResult sendSMS(String phone){
        //模拟调用短信接口
        String result = tbPhoneService.sendSMS(phone);
        return AppResult.successResult(result);
    }
    @GetMapping("/verifySMS")
    @Operation(summary = "发短信接口")
    public AppResult verifySMS(String phone,String code){
        //模拟调用短信接口
        String result = tbPhoneService.verifySMS(phone,code);
        return AppResult.successResult(result);
    }
}

