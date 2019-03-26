package com.example.demo.order.menu.dto;

import com.example.demo.data.BaseDto;

import java.math.BigDecimal;

/**
 * Created by JIAYI_TAN on 2019/3/7.
 */
public class AddMenuDto extends BaseDto{
    private int menuId;
    private int  typeId;
    private String name;
    private BigDecimal price;
    private String remark;
    private String imageName;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
