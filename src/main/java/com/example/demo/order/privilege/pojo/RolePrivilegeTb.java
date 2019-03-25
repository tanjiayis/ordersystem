package com.example.demo.order.privilege.pojo;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
@Entity
@Table(name = "role_privilege_tb")
public class RolePrivilegeTb {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "privilege_id")
    private int privilegeId;

    public RolePrivilegeTb() {
    }

    public RolePrivilegeTb(int roleId, int privilegeId) {
        this.roleId = roleId;
        this.privilegeId = privilegeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }
}
