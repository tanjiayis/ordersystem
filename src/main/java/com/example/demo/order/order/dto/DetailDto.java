package com.example.demo.order.order.dto;

import com.example.demo.data.BaseDto;

import javax.validation.constraints.NotNull;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
public class DetailDto extends BaseDto{
    @NotNull(message = "订单Id不能为空!")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
