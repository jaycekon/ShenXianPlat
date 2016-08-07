package com.Shop.Util;

import com.Shop.beans.OrderProduct;

/**
 * Created by Administrator on 2016/8/7.
 */
public class OrderProductPojo {
    private OrderProduct orderProduct;

    public OrderProductPojo(){

    }

    public OrderProductPojo(OrderProduct orderProduct){
        this.orderProduct = orderProduct;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }
}
