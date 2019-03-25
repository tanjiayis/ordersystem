package com.example.demo.order.privilege.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
public class AddRoleDto {
    @NotEmpty(message = "不能为空!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
