package com.example.demo.order.order.dto;

import com.example.demo.data.BaseDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by JIAYI_TAN on 2019/3/5.
 */
public class FilterOrderDto extends BaseDto {
    private String tableSn;
    private String startTime;
    private String endTime;
    private String complete = "all";

    public String getTableSn() {
        return tableSn;
    }

    public void setTableSn(String tableSn) {
        this.tableSn = tableSn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }
}
