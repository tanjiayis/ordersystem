package com.example.demo.order.order.model;

import java.util.Date;

/**
 * Created by JIAYI_TAN on 2019/3/15.
 */
public class DayOrder {
    private int orderCount;
    private Date dateDay;

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }
}
