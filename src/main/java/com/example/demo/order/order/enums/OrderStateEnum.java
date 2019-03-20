package com.example.demo.order.order.enums;

/**
 * Created by JIAYI_TAN on 2019/3/10.
 */
public enum OrderStateEnum {
    todocompleted("todocompleted", "未完成"),
    completed("completed", "已完成");
    private final String code;
    private final String name;
    OrderStateEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
