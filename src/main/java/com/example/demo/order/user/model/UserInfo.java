package com.example.demo.order.user.model;

import com.example.demo.order.user.pojo.User;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 */
public class UserInfo extends User{
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
