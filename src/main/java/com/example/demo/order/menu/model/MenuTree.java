package com.example.demo.order.menu.model;

import com.example.demo.order.menu.pojo.Menu;

import java.util.List;

/**
 * Created by JIAYI_TAN on 2019/2/25.
 */
public class MenuTree {
    private int id;
    private String typeName;
    private List<Menu> menus;

    public MenuTree() {
    }

    public MenuTree(int id, String typeName, List<Menu> menus) {
        this.id = id;
        this.typeName = typeName;
        this.menus = menus;
    }

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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
