package com.example.demo.order.menu.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
public class AddTypeDto {
    private int id;
    @NotEmpty(message = "分类名称不能为空!")
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
