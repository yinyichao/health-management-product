package com.yins.health.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.yins.health.dto.AccountDto;
import com.yins.health.dto.AccountRegisterDto;
import com.yins.health.entity.Account;
import com.yins.health.vo.LoginVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AccountService extends IService<Account> {
    void register(AccountRegisterDto req);

    String uploadAvatar(MultipartFile file);

    LoginVo login(String  payLoad);

    AccountDto queryDetail(Long id);

    void logout(HttpServletRequest request);

    void update(AccountRegisterDto req);
}
