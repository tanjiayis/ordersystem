package com.example.demo.order.order.dto;

import com.example.demo.data.BaseDto;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
public class AddOrderDto extends BaseDto {
    private String tableSn;
    private String menuIds;

    public String getTableSn() {
        return tableSn;
    }

    public void setTableSn(String tableSn) {
        this.tableSn = tableSn;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
