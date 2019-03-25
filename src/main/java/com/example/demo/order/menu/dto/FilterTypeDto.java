package com.example.demo.order.menu.dto;

import com.example.demo.data.BaseDto;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
public class FilterTypeDto extends BaseDto {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
