package com.example.demo.order.order.model;

import com.example.demo.order.menu.pojo.Menu;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
public class Menus {
    private int menuNum;
    private Menu menu;

    public Menus() {
    }

    public Menus(int menuNum, Menu menu) {
        this.menuNum = menuNum;
        this.menu = menu;
    }

    public int getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(int menuNum) {
        this.menuNum = menuNum;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
