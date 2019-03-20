package com.example.demo.order.order.model;

import com.example.demo.order.menu.pojo.Menu;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
public class OrderInfo{
    private int orderId;
    private Date createTime;
    private String tableSn;
    private BigDecimal totalPrice;
    private String menusIds;
    private List<Menus> menus;

    public OrderInfo() {
    }

    public OrderInfo(int orderId, Date createTime, String tableSn, BigDecimal totalPrice, String menusIds, List<Menus> menus) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.tableSn = tableSn;
        this.totalPrice = totalPrice;
        this.menusIds = menusIds;
        this.menus = menus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableSn() {
        return tableSn;
    }

    public void setTableSn(String tableSn) {
        this.tableSn = tableSn;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMenusIds() {
        return menusIds;
    }

    public void setMenusIds(String menusIds) {
        this.menusIds = menusIds;
    }

    public List<Menus> getMenus() {
        return menus;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }
}
