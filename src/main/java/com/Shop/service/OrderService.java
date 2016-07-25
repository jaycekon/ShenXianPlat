package com.Shop.service;

import com.Shop.Util.OrderDetailPojo;
import com.Shop.beans.Address;
import com.Shop.beans.Cart;
import com.Shop.beans.OrderProduct;
import com.Shop.beans.Orders;
import com.Shop.dao.CartDao;
import com.Shop.dao.OrderProductDao;
import com.Shop.dao.OrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
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

    public  OrderDetailPojo addOrders(int userId,Address address,String[] orderProductId){
        Cart cart = cartDao.findByUserId(userId);
        Orders orders =new Orders();
        orders.setUserId(userId);
//        orders.setCount(cart.getCount());
//        orders.setPrices(cart.getPrices());
        int count = 0;
        float prices = 0;
        List<OrderProduct> os = new ArrayList<>();
        orders.setSetDate(new Date());
        orders.setUserAddress(address.getDetails());
        orders.setUserPhone(address.getUserphone());
        orders.setUserName(address.getUsername());
        ordersDao.addAnyType(orders);
        List<OrderProduct> orderProducts = orderProductDao.findByCartId(cart.getId());
        for(OrderProduct orderProduct:orderProducts){
            for(String str:orderProductId) {
                if (str.equals(String.valueOf(orderProduct.getId()))) {
                    orderProduct.setCartId(0);
                    os.add(orderProduct);
                    orderProduct.setOrderId(orders.getId());
                    count += orderProduct.getCount();
                    prices += orderProduct.getProductPrices() * orderProduct.getCount();
                    orderProductDao.updateAnyType(orderProduct);
                }
            }
        }
        orders.setCount(count);
        orders.setPrices(prices);
        cart.setCount(cart.getCount()-count);
        cart.setPrices(cart.getPrices() - prices);
        ordersDao.updateAnyType(orders);
        cartDao.updateAnyType(cart);
        OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
        orderDetailPojo.setOrders(orders);
        orderDetailPojo.setOrderProducts(os);
        return orderDetailPojo;
    }

    public List<OrderDetailPojo> findOrdersByUserId(int userId){
        List<Orders> orders = ordersDao.findOrdersByUserId(userId);
        List<OrderDetailPojo> list = new ArrayList<>();
        for(Orders order :orders){
            OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
            orderDetailPojo.setOrders(order);
            List<OrderProduct> orderProducts = orderProductDao.findByOrderId(order.getId());
            orderDetailPojo.setOrderProducts(orderProducts);
            list.add(orderDetailPojo);
        }
        return list;
    }
}
