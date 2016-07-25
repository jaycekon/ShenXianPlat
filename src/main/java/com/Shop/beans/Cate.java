package com.Shop.beans;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/28.
 */
@Entity
@Table
public class Cate {
    private int id;
    private String name;
    private Cate cate;

    @Id
    @GeneratedValue
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

    @ManyToOne
    public Cate getCate() {
        return cate;
    }

    public void setCate(Cate cate) {
        this.cate = cate;
    }
}
