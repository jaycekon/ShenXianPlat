package com.Shop.controller;

import com.Shop.Util.CartPojo;
import com.Shop.beans.Cart;
import com.Shop.beans.OrderProduct;
import com.Shop.beans.User;
import com.Shop.service.BuyService;
import com.Shop.service.UserService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
@Controller
public class BuyController {
    @Autowired
    private BuyService buyService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="buyGood",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buyGood(int id, int count, HttpSession session,HttpServletRequest request){
//        User loginUser = (User)session.getAttribute("loginUser");
        User loginUser = userService.loadUser(1);
        if(request.getParameter("phone")!=null){
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            loginUser = userService.loginUser(phone,password);
        }
        JsonObject jsonObject = new JsonObject();
        if(loginUser ==null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","用户未登录");
            return jsonObject.toString();
        }
        String json = buyService.buyGood(loginUser,id,count);
        return json;
    }


    @RequestMapping(value="removeGood",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String removeGood(int id,HttpSession session){
//        User loginUser = (User)session.getAttribute("loginUser");
        String json = buyService.removeGood(id);
        return json;
    }

    @RequestMapping(value="updateOrderProduct",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateOrderProduct(int count,int id){
        String json = buyService.updateOrderproduct(id,count);
        return json;
    }

    @RequestMapping(value="myCart",method = RequestMethod.GET)
    @ResponseBody
    public CartPojo myCart(HttpSession session){
//        User user= (User)session.getAttribute("loginUser");
        User user = userService.loadUser(1);
        Cart cart = userService.findCartByUserId(user.getId());
        CartPojo cartPojo = new CartPojo();
        cartPojo.setCart(cart);
        if(cart!=null){
            List<OrderProduct> orderProducts = buyService.myCart(cart.getId());
            cartPojo.setOrderProducts(orderProducts);
        }
        return cartPojo;
    }
}
