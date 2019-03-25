package com.example.demo.order.privilege.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SetPermissionDto{

    @NotNull(message = "未指定角色信息")
    private int roleId;
    @NotNull(message = "未赋予任何权限")
    @NotEmpty(message = "未赋予任何权限")
    private String[] privileges;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String[] getPrivileges() {
        return privileges;
    }
    public void setPrivileges(String[] privileges) {
        this.privileges = privileges;
    }
}
