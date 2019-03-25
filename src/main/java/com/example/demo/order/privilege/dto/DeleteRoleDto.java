package com.example.demo.order.privilege.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
public class DeleteRoleDto {
    @NotNull(message = "没有角色信息!")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
