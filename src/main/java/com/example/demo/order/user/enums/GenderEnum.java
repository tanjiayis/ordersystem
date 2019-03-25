package com.example.demo.order.user.enums;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 */
public enum GenderEnum {
    man("man", "男"),
    women("women", "女"),
    other("other", "其他");
    private final String code;
    private final String value;

    GenderEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
