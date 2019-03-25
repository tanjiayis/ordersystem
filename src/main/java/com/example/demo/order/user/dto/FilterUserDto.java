package com.example.demo.order.user.dto;

import com.example.demo.data.BaseDto;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 */
public class FilterUserDto extends BaseDto {
    private String realName;
    private int roleId;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
