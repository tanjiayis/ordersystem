package com.example.demo.web.dto;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 * 退餐接收的参数
 */
public class SubMenuDto {
    private int menuId;
    private int orderId;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
