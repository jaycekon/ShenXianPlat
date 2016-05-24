/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.utils;

import java.util.List;


public class Pager {

    private int offset;         //记录开始的位置
    private int maxPageSize;    //页面的最大数量
    private long total;          //总记录数
    private List datas;         //页面数据

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }

    public int getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    @Override
    public String toString() {
        return "Pager{" + "offset=" + offset + ", maxPageSize=" + maxPageSize + ", total=" + total + '}';
    }

}
