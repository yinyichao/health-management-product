package com.yins.health.util;

import com.yins.health.common.ErrorMessageConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 通用返回对象
 * @author lengyj
 * @since 2020.06.19
 * */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class    AppResult<T> {
    /**错误码*/
    private Integer code;
    /**错误信息*/
    private String message;
    /**数据*/
    private T data;

    public static <T> AppResult<T> successResult(T data){
        return AppResult.<T>builder()
                .code(ErrorMessageConstant.SUCCESS)
                .message(ErrorMessageConstant.MSG_SUCCESS)
                .data(data)
                .build();
    }

    public static <T> AppResult<T> emptyResult(){
        return AppResult.<T>builder()
                .code(ErrorMessageConstant.SUCCESS)
                .message(ErrorMessageConstant.MSG_SUCCESS)
                .data(null)
                .build();
    }

    public static <T> AppResult<T> failedResult(Integer code, String errorMessage){
        return failedResult(code, errorMessage, null);
    }

    public static <T> AppResult<T> failedResult(Integer code, String errorMessage, T t){
        return AppResult.<T>builder()
                .code(code)
                .message(errorMessage)
                .data(t)
                .build();
    }

    /**
     * 两种格式完全是一样的，只是为了转成有Swagger API信息的数据
     */
    public ResponseObject<T>  toApiResponseObject(){
        return new ResponseObject<>(this);
    }
}
