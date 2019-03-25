package com.example.demo.order.user.pojo;

import com.example.demo.order.user.enums.GenderEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
@Table(name = "user")
@Entity
public class User implements Serializable{

    public static User getUserInfo(){
        User user  = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return user;
    }

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;
    @Column(name = "realname")
    private String realName;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    public User() {
    }

    public User(int roleId, String username, String password, GenderEnum gender, String realName, String mobile, Date createTime, Date updateTime) {
        this.roleId = roleId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.realName = realName;
        this.mobile = mobile;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
