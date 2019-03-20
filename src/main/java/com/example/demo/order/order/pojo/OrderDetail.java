package com.example.demo.order.order.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@Table(name = "orderdetail")
@Entity
public class OrderDetail {
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "menu_id")
    private int menuId;
    @Column(name = "menu_num")
    private int menuNum;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int menuId, int menuNum) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.menuNum = menuNum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(int menuNum) {
        this.menuNum = menuNum;
    }
}
