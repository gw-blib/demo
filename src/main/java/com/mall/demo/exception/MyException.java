package com.mall.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常信息
}
