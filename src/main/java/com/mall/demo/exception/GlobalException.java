package com.mall.demo.exception;

import com.mall.demo.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MyException.class)
    public R error(MyException e){
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
