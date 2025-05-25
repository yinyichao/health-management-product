package com.yins.health.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.yins.health.constant.ReturnCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
public class BaseResponse {

    @Builder.Default
    @JSONField(name = "code")
    @ApiModelProperty(notes = "返回码", example = "1")
    protected int code = ReturnCode.SUCCESS.getCode();
    @ApiModelProperty(notes = "消息", example = "成功")
    @JSONField(name = "message")
    protected String message = ReturnCode.SUCCESS.getText();

    public BaseResponse() {
        this.code = ReturnCode.SUCCESS.getCode();
        this.message = ReturnCode.SUCCESS.getText();
    }

    public BaseResponse(int errorCode) {
        this.code = errorCode;
    }

    public BaseResponse(ReturnCode codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getText();
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }



    public BaseResponse setMsg(String msg) {
        this.message = msg;
        return this;
    }

    public BaseResponse setCode(ReturnCode codeEnum) {
        this.code = codeEnum.getCode();
        return this;
    }

}
