package com.example.demo.order.menu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
@Table(name = "menu")
@Entity
public class Menu {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "typeid")
    private int typeId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "image")
    private String image;
    @Column(name = "remark")
    private String remark;
    @Column(name = "state")
    private boolean state;

    public Menu() {
    }

    public Menu(int typeId, String name, BigDecimal price, String image, String remark, boolean state) {
        this.typeId = typeId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.remark = remark;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRemark() {
        return remark==null?"":remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
