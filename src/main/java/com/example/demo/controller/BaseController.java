package com.example.demo.controller;

/**
 * Created by JIAYI_TAN on 2019/3/4.
 */
public abstract class BaseController {
    String getMessage(Exception e){
        return e.getMessage() == null ? "服务器除了小问题 , 稍后重试!" : e.getMessage();
    }
}
