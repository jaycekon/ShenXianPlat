package com.Shop.Util;

import com.Shop.beans.Orders;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class OrdersPojo {
    private List<OrderDetailPojo> orderses;

    public List<OrderDetailPojo> getOrderses() {
        return orderses;
    }

    public void setOrderses(List<OrderDetailPojo> orderses) {
        this.orderses = orderses;
    }
}
