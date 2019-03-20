package com.example.demo.order.menu.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
public class DetailDto {
    @NotNull(message = "不能为空！")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
