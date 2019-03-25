package com.example.demo.order.order.pojo;

import com.example.demo.order.order.enums.OrderStateEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@Table(name = "ordertb")
@Entity
public class Order  implements Serializable{
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "table_sn")
    private String tableSn;
    @Column(name = "menuid")
    private String menuId;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "create_time")
    private Date createTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "complete")
    private OrderStateEnum complete;

    public Order() {
    }

    public Order(String tableSn, String menuId, BigDecimal totalPrice, Date createTime, OrderStateEnum complete) {
        this.tableSn = tableSn;
        this.menuId = menuId;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableSn() {
        return tableSn;
    }

    public void setTableSn(String tableSn) {
        this.tableSn = tableSn;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public OrderStateEnum getComplete() {
        return complete;
    }

    public void setComplete(OrderStateEnum complete) {
        this.complete = complete;
    }
}
