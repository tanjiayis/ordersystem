package com.example.demo.order.menu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@Table(name = "menutype")
@Entity
public class MenuType {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type_name")
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
