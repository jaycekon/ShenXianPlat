package com.Shop.Util;

import com.Shop.beans.OrderProduct;
import com.Shop.beans.Orders;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class OrderDetailPojo {
    private Orders orders;
    private List<OrderProductPojo> orderProducts;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<OrderProductPojo> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductPojo> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
