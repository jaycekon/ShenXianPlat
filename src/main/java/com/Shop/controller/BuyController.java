package com.Shop.controller;

import com.Shop.Util.CartPojo;
import com.Shop.beans.*;
import com.Shop.service.BuyService;
import com.Shop.service.CommentService;
import com.Shop.service.OrderService;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value="buyGood",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buyGood(int id, int count, HttpSession session,HttpServletRequest request){
        User loginUser = (User)session.getAttribute("loginUser");
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

    @RequestMapping(value="myCart",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object myCart(HttpSession session,HttpServletRequest request){
        User loginUser= (User)session.getAttribute("loginUser");
        JsonObject jsonObject = new JsonObject();
        if(loginUser ==null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("errorMsg","用户未登录");
            return jsonObject.toString();
        }
        Cart cart = userService.findCartByUserId(loginUser.getId());
        CartPojo cartPojo = new CartPojo();
        cartPojo.setCart(cart);
        if(cart!=null){
            List<OrderProduct> orderProducts = buyService.myCart(cart.getId());
            cartPojo.setOrderProducts(orderProducts);
        }
        return cartPojo;
    }

    @RequestMapping(value="addCount",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addCount(int id){
        boolean flag = buyService.addCount(id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",flag);
        return jsonObject.toString();
    }


    @RequestMapping(value="subCount",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String subCount(int id){
        boolean flag = buyService.subCount(id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",flag);
        return jsonObject.toString();
    }

    @RequestMapping(value="Comment",method={RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addComment(int orderId,int orderProductId,Comment comment,HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        JsonObject jsonObject = new JsonObject();
        if(user==null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("errorMsg","用户未登录");
            return jsonObject.toString();
        }
        Orders orders = orderService.findOrder(orderId);
        if(orders.getStatus()!=3){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("errorMsg","订单未完成，无法评论");
            return jsonObject.toString();
        }
        OrderProduct orderProduct = orderService.findOrderProduct(orderProductId);
        if(orderProduct.getComment()==1){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("errorMsg","该商品已评论");
            return jsonObject.toString();
        }
        orderProduct.setComment(1);
        orderService.updateOrderProduct(orderProduct);
        comment.setGoodId(orderProduct.getProductId());
        comment.setOrderProductId(orderProduct.getId());
        commentService.addComment(comment,user);
        jsonObject.addProperty("status",0);
        return jsonObject.toString();
    }
}
