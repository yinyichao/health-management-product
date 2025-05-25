package com.yins.health.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.yins.health.constant.ReturnCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "返回单个对象")
public class ResponseObject<T> extends BaseResponse {
    @JSONField(name = "data")
    @ApiModelProperty(notes = "返回数据")
    private T data;

    public ResponseObject() {
    }

    public ResponseObject(T data) {
        this.data = data;
    }

    public ResponseObject<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseObject<T> success(ReturnCode codeEnum) {
        code = codeEnum.getCode();
        message = codeEnum.getText();
        return this;
    }

    public ResponseObject<T> success(ReturnCode codeEnum,T data) {
        code = codeEnum.getCode();
        message = codeEnum.getText();
        this.data = data;
        return this;
    }

    public ResponseObject<T> error(ReturnCode codeEnum,String msg) {
        this.code = codeEnum.getCode();
        this.message = msg;
        return this;
    }

    public ResponseObject<T> error(ReturnCode codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getText();
        return this;
    }

    public ResponseObject<T> error(ReturnCode codeEnum,T data) {
        code = codeEnum.getCode();
        message = codeEnum.getText();
        this.data = data;
        return this;
    }

    /**
     * 两种格式完全是一样的，只是为了转成有Swagger API信息的数据
     * @param app
     */
    public ResponseObject(AppResult<T> app){
        super(app.getCode(), app.getMessage());
        this.data = app.getData();
    }

}
