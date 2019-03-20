package com.example.demo.order.table.dto;

import com.example.demo.data.BaseDto;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
public class FilterTableDto extends BaseDto{
    private String tableSn;
    private String  num;
    private boolean state;

    public String getTableSn() {
        return tableSn;
    }

    public void setTableSn(String tableSn) {
        this.tableSn = tableSn;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
