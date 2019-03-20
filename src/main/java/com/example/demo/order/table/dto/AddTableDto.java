package com.example.demo.order.table.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
public class AddTableDto {
    @NotEmpty(message = "桌子编号不能为空")
    private String tableSn;
    @NotEmpty(message = "人数不能为空")
    private String num;

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
}
