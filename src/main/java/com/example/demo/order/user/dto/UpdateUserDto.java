package com.example.demo.order.user.dto;

import com.example.demo.order.user.enums.GenderEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by JIAYI_TAN on 2019/3/23.
 */
public class UpdateUserDto {
    private int userId;
    @NotEmpty(message = "用户名不能为空!")
    private String username;
    private String password;
    private String realName;
    @NotEmpty(message = "联系电话不能为空!")
    private String mobile;
    private GenderEnum gender;
    @NotNull(message = "所属角色不能为空!")
    private int roleId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
