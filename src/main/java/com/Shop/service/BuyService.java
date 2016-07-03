package com.Shop.service;

import com.Shop.beans.Cart;
import com.Shop.beans.OrderProduct;
import com.Shop.beans.Product;
import com.Shop.beans.User;
import com.Shop.dao.CartDao;
import com.Shop.dao.OrderProductDao;
import com.Shop.dao.ProductDao;
import com.Shop.dao.UserDao;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class BuyService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderProductDao orderProductDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartDao cartDao;

    public String buyGood(User user,int productId,int count){
        JsonObject jsonObject = new JsonObject();
        int flag=0;
        Cart cart = cartDao.findByUserId(user.getId());
        if(cart == null){
            flag=1;
            cart = new Cart();
            cart.setUserId(user.getId());
            cart.setCount(0);
            cart.setPrices(0);
        }
        Product product = productDao.findById(productId,"Product");
        if(product.getNum()<count){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","库存不足");
            return jsonObject.toString();
        }
        product.setNum(product.getNum()-count);
        product.setSaleCount(product.getSaleCount()+count);

        cart.setCount(cart.getCount()+count);
        cart.setPrices(cart.getPrices()+product.getPrice()*count);
        if(flag ==0){
            System.out.println(cart.getCount());
            System.out.println(cart.getPrices());
            cartDao.updateAnyType(cart);
        }else{
            System.out.println(cart.getCount());
            System.out.println(cart.getPrices());
            cartDao.addAnyType(cart);
        }
        flag = 0;
        OrderProduct orderProduct = orderProductDao.findByCartIdAndProductId(cart.getId(),productId);
        if(orderProduct==null){
            orderProduct = new OrderProduct();
            flag = 1;
        }
        orderProduct.setCount(orderProduct.getCount()+count);
        orderProduct.setProductDescripes(product.getName());
        orderProduct.setProductName(product.getName());
        orderProduct.setProductPrices(product.getPrice());
        orderProduct.setProductId(productId);
        orderProduct.setCartId(cart.getId());
        if (flag == 0) {
            orderProductDao.updateAnyType(orderProduct);
        }else {
            orderProductDao.addAnyType(orderProduct);
        }
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }

    public List<OrderProduct>  myCart(int cartId){
        return orderProductDao.findByCartId(cartId);
    }

    public String removeGood(int id){
        OrderProduct orderProduct  = orderProductDao.findById(id,"OrderProduct");
        JsonObject jsonObject = new JsonObject();
        if(orderProduct==null) {
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","找不到对应项");
            return jsonObject.toString();
        }
        Cart cart = cartDao.findById(orderProduct.getCartId(), "Cart");
        cart.setCount(cart.getCount()-orderProduct.getCount());
        cart.setPrices(cart.getPrices()-orderProduct.getProductPrices()*orderProduct.getCount());
        cartDao.updateAnyType(cart);
        orderProductDao.deleteAnyType(orderProduct);
        jsonObject.addProperty("status",true);
        return jsonObject.toString();

    }

    public String updateOrderproduct(int id,int count){
        JsonObject jsonObject = new JsonObject();
        OrderProduct orderProduct = orderProductDao.findById(id,"OrderProduct");
        if(orderProduct==null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","找不到该项");
            return  jsonObject.toString();
        }
        int num = orderProduct.getCount();
        Cart cart = cartDao.findById(orderProduct.getCartId(),"Cart");
        if(cart==null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","找不到该项");
            return  jsonObject.toString();
        }
        if(num>count){
            num = num-count;
            cart.setCount(cart.getCount()-num);
            cart.setPrices(cart.getPrices()-num*orderProduct.getProductPrices());
        }else{
            num = count - num;
            cart.setCount(cart.getCount()+num);
            cart.setPrices(cart.getPrices()+num*orderProduct.getProductPrices());
        }
        cartDao.updateAnyType(cart);
        orderProduct.setCount(count);
        orderProductDao.updateAnyType(orderProduct);
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }
}
