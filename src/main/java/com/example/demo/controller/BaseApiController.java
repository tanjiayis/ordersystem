package com.example.demo.controller;

import com.example.demo.data.CommonResult;
import com.example.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by JIAYI_TAN on 2019/3/4.
 */
public abstract class BaseApiController extends BaseController {
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    CommonResult handleAllException(BusinessException ex, HttpServletResponse response) throws IOException{
        ex.printStackTrace();
        return new CommonResult(-10, getMessage(ex));
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    CommonResult handleAllException(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        ex.printStackTrace();
        return new CommonResult(-4, getMessage(ex));
    }
}
