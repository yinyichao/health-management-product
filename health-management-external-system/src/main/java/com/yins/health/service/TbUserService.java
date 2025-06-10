package com.yins.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.AgentConfigParam;
import com.yins.health.dto.AccountDto;
import com.yins.health.dto.AccountRegisterDto;
import com.yins.health.entity.TbUser;
import com.yins.health.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户表;(TbUser)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
public interface TbUserService extends IService<TbUser> {

    void register(AccountRegisterDto req);

    LoginVo login(String payLoad);

    AccountDto queryDetail(String id);

    void logout(HttpServletRequest request);

    void update(AccountRegisterDto req);

    LoginVo authentication(String code);

    void weixin() throws Exception;

    AgentConfigParam obtainConfigParam(String url);
}

