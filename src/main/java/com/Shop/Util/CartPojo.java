package com.Shop.Util;

import com.Shop.beans.Cart;
import com.Shop.beans.OrderProduct;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class CartPojo {
    private Cart cart;
    private List<OrderProduct> orderProducts;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
