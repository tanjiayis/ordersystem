package com.example.demo.web.model;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JIAYI_TAN on 2019/3/19.
 */
public class MyOrder2 {
    private Date createTime;
    private BigDecimal totalPrice;

    public MyOrder2() {
    }

    public MyOrder2(Date createTime, BigDecimal totalPrice) {
        this.createTime = createTime;
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
