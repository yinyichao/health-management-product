package com.yins.health.vo;

import com.yins.health.dto.AccountDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/*
*TODO
*@Author Mister
*@Date 2025/3/27 15:47
*/
@Data
public class LoginVo {
    @Schema(description = "用户信息")
    private AccountDto accountDTO;
    @Schema(description = "token")
    private String token;

    public LoginVo() {
    }

    public LoginVo(AccountDto accountDTO, String token) {
        this.accountDTO = accountDTO;
        this.token = token;
    }
}

