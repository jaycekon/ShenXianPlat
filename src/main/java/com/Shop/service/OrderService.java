package com.Shop.service;

import com.Shop.beans.Address;
import com.Shop.beans.Cart;
import com.Shop.beans.OrderProduct;
import com.Shop.beans.Orders;
import com.Shop.dao.CartDao;
import com.Shop.dao.OrderProductDao;
import com.Shop.dao.OrdersDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class OrderService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private OrderProductDao orderProductDao;

    public  void addOrders(int userId,Address address){
        Cart cart = cartDao.findByUserId(userId);
        Orders orders =new Orders();
        orders.setUserId(userId);
        orders.setCount(cart.getCount());
        orders.setPrices(cart.getPrices());
        orders.setSetDate(new Date());
        orders.setUserAddress(address.getDetails());
        orders.setUserPhone(address.getPhone());
        orders.setUserName(address.getUsername());
        ordersDao.addAnyType(orders);
        List<OrderProduct> orderProducts = orderProductDao.findByCartId(cart.getId());
        for(OrderProduct orderProduct:orderProducts){
            orderProduct.setCartId(0);
            orderProduct.setOrderId(orders.getId());
            orderProductDao.updateAnyType(orderProduct);
        }
        cartDao.deleteAnyType(cart);
    }

    public List<Orders> findOrdersByUserId(int userId){
        return ordersDao.findOrdersByUserId(userId);
    }
}
