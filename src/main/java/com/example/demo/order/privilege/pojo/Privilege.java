package com.example.demo.order.privilege.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JIAYI_TAN on 2019/3/22.
 */
@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public Privilege() {
    }

    public Privilege(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
