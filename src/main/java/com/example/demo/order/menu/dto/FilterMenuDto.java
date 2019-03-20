package com.example.demo.order.menu.dto;

import com.example.demo.data.BaseDto;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
public class FilterMenuDto extends BaseDto {
    private String name;
    private int type;
    private boolean state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
