package com.Shop.service;

import com.Shop.Util.OrderDetailPojo;
import com.Shop.Util.OrderProductPojo;
import com.Shop.beans.Address;
import com.Shop.beans.Cart;
import com.Shop.beans.OrderProduct;
import com.Shop.beans.Orders;
import com.Shop.dao.CartDao;
import com.Shop.dao.OrderProductDao;
import com.Shop.dao.OrdersDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.text.SimpleDateFormat;
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
    Logger log = Logger.getLogger(this.getClass());
    public  OrderDetailPojo addOrders(int userId,Address address,String[] orderProductId){
        Cart cart = cartDao.findByUserId(userId);
        Orders orders =new Orders();
        orders.setUserId(userId);
//        orders.setCount(cart.getCount());
//        orders.setPrices(cart.getPrices());
        int count = 0;
        float prices = 0;
        List<OrderProduct> os = new ArrayList<>();
        String data = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data);
        orders.setSetDate(simpleDateFormat.format(new Date()));
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
            List<OrderProduct> orderProductPojos = new ArrayList<>();
            for(OrderProduct orderProduct:orderProducts){
                orderProductPojos.add(orderProduct);
            }
            orderDetailPojo.setOrderProducts(orderProductPojos);
            list.add(orderDetailPojo);
        }
        return list;
    }

    public List<OrderDetailPojo> findAll(){
        List<Orders> orders = ordersDao.findAll("Orders");
        List<OrderDetailPojo> list = new ArrayList<>();
        for(Orders order :orders){
            OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
            orderDetailPojo.setOrders(order);
            List<OrderProduct> orderProducts = orderProductDao.findByOrderId(order.getId());
            List<OrderProduct> orderProductPojos = new ArrayList<>();
            for(OrderProduct orderProduct:orderProducts){
                orderProductPojos.add(orderProduct);
            }
            orderDetailPojo.setOrderProducts(orderProductPojos);
            list.add(orderDetailPojo);
        }
        return list;
    }


    public List<OrderDetailPojo> findByStatus(int status){
        List<Orders> orders = ordersDao.findOrdersByStatus(status);
        List<OrderDetailPojo> list = new ArrayList<>();
        for(Orders order :orders){
            OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
            orderDetailPojo.setOrders(order);
            List<OrderProduct> orderProducts = orderProductDao.findByOrderId(order.getId());
            List<OrderProduct> orderProductPojos = new ArrayList<>();
            for(OrderProduct orderProduct:orderProducts){
                orderProductPojos.add(orderProduct);
            }
            orderDetailPojo.setOrderProducts(orderProductPojos);
            list.add(orderDetailPojo);
        }
        return list;
    }

    public void updateOrders(Orders orders){
        ordersDao.updateAnyType(orders);
    }

    public Orders findOrder(int id){
        return ordersDao.findById(id,"Orders");
    }

    public OrderDetailPojo findOrderDetail(int id,int userId){
        Orders orders = ordersDao.findOrderByIdAndUserId(id,userId);
        OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
        orderDetailPojo.setOrders(orders);
        List<OrderProduct> orderProducts  = orderProductDao.findByOrderId(orders.getId());
        List<OrderProduct> orderProductPojos = new ArrayList<>();
        for(OrderProduct orderProduct:orderProducts){
            orderProductPojos.add(orderProduct);
        }
        orderDetailPojo.setOrderProducts(orderProductPojos);
        return orderDetailPojo;
    }

    public OrderDetailPojo findOrderDetail(int id){
        Orders orders = ordersDao.findById(id,"Orders");
        OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
        orderDetailPojo.setOrders(orders);
        List<OrderProduct> orderProducts  = orderProductDao.findByOrderId(orders.getId());
        List<OrderProduct> orderProductPojos = new ArrayList<>();
        for(OrderProduct orderProduct:orderProducts){
            orderProductPojos.add(orderProduct);
        }
        orderDetailPojo.setOrderProducts(orderProductPojos);
        return orderDetailPojo;
    }

    public OrderProduct findOrderProduct(int id){
        return orderProductDao.findById(id,"OrderProduct");
    }

    public void updateOrderProduct(OrderProduct orderProduct){
        orderProductDao.updateAnyType(orderProduct);
    }

    public boolean deleteOrder(int orderId,int userId){
        Orders orders = ordersDao.findById(orderId,"Orders");
        log.info(orders.getUserId());
        if(orders.getUserId()!=userId){
            return false;
        }
        List<OrderProduct> orderProducts = orderProductDao.findByOrderId(orderId);
        for(OrderProduct orderProduct:orderProducts){
            orderProductDao.deleteAnyType(orderProduct);
        }
        ordersDao.deleteAnyType(orders);
        return true;
    }

    public List<OrderProduct> findOrderPorductByCartId(int cartId){
        return orderProductDao.findByCartId(cartId);
    }
}
