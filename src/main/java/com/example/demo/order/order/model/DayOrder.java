package com.example.demo.order.order.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by JIAYI_TAN on 2019/3/15.
 */
public class DayOrder {
    private int orderCount;
    private String dateDay;
    private BigDecimal totalPrice;

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getDateDay() {
        return dateDay;
    }

    public void setDateDay(String dateDay) {
        this.dateDay = dateDay;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
