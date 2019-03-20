package com.example.demo.order.table.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JIAYI_TAN on 2019/2/22.
 */
@Table(name = "diningtable")
@Entity
public class DiningTable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "tablesn")
    private String tableSn;
    @Column(name = "num")
    private String num;
    @Column(name = "flag")
    private boolean flag;
    @Column(name = "deleted")
    private  boolean deleted;
    @Column(name = "code")
    private String code;

    public DiningTable(String tableSn, String num, boolean flag) {
        this.tableSn = tableSn;
        this.num = num;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableSn() {
        return tableSn;
    }

    public void setTableSn(String tableSn) {
        this.tableSn = tableSn;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
