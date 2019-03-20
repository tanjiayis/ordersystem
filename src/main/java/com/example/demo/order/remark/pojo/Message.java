package com.example.demo.order.remark.pojo;

import org.springframework.stereotype.Controller;

import javax.naming.NamingEnumeration;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@Table(name = "message")
@Entity
public class Message {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "content")
    private String content;

    public Message() {
    }

    public Message(Date createTime, String content) {
        this.createTime = createTime;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
