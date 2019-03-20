package com.example.demo.order.remark.dto;

import com.example.demo.data.BaseDto;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
public class FilterRemarkDto extends BaseDto{
    private String startTime;
    private String endTime;

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
}
