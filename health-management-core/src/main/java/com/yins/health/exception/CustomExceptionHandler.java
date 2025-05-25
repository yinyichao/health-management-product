package com.yins.health.exception;

import com.yins.health.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author Darius
 * @Version 1.0
 **/
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R handler(Exception e){

        if(e instanceof BizException){
            log.error("[业务异常]{}",e);
            BizException bizException =  (BizException) e;
            return R.fail(bizException.getCode(),bizException.getMsg());
        }else {
            log.error("[系统异常]{}",e);
            return R.fail("系统异常");
        }

    }

}