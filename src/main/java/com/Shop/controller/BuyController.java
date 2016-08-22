package com.Shop.controller;

import com.Shop.Util.CartPojo;
import com.Shop.Util.OrderDetailPojo;
import com.Shop.beans.*;
import com.Shop.service.*;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private AddressService addressService;
    Logger logger = Logger.getLogger(this.getClass());
    @RequestMapping(value="buyGood",method= {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String buyGood(int id, int count, HttpSession session,HttpServletRequest request){
        User loginUser = (User)session.getAttribute("loginUser");
//        User loginUser = userService.loadUser(1);
        JsonObject jsonObject = new JsonObject();
        if(session.getAttribute("loginUser")==null){
            if(request.getParameter("userId")!=null) {
                int uId = Integer.parseInt(request.getParameter("userId"));
                loginUser = userService.loadUser(uId);
            }
        }
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
    public Object myCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        User loginUser= (User)session.getAttribute("loginUser");
        if(session.getAttribute("loginUser")==null){
            int uId= Integer.parseInt(request.getParameter("userId"));
            loginUser = userService.loadUser(uId);
        }
//        User loginUser = userService.loadUser(1);
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
        String jsonObject = buyService.addCount(id);
        return jsonObject;
    }


    @RequestMapping(value="subCount",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String subCount(int id){
        String jsonObject = buyService.subCount(id);
        return jsonObject;
    }

    @RequestMapping(value="Comment",method={RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addComment(int orderId,int orderProductId,Comment comment,HttpSession session){
        User user = (User)session.getAttribute("loginUser");
//        User user = userService.loadUser(1);
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


    @RequestMapping(value="updateStatus",method={RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateStatus(int status,int orderId){
        Orders orders = orderService.findOrder(orderId);
        if(status==1){
            String data = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data);
            orders.setPayDate(simpleDateFormat.format(new Date()));
        }
        orders.setStatus(status);
        orderService.updateOrders(orders);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status","0");
        return jsonObject.toString();
    }

    @RequestMapping(value="deleteOrders",method={RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteOrders(int ordersId,HttpSession session,HttpServletRequest request){
        User user= (User)session.getAttribute("loginUser");
        if(user==null){
            if(request.getParameter("userId")!=null){
                int userId = Integer.parseInt(request.getParameter("userId"));
                user = userService.loadUser(userId);
            }
        }
        JsonObject jsonObject = new JsonObject();
        if(user ==null){
            jsonObject.addProperty("status","1");
            jsonObject.addProperty("errMsg","用户未登录");
            return jsonObject.toString();
        }
        boolean flag = orderService.deleteOrder(ordersId,user.getId());
        if(!flag){
            jsonObject.addProperty("status","1");
            jsonObject.addProperty("errMsg","该订单不属于您！");
            return jsonObject.toString();
        }
        jsonObject.addProperty("status","0");
        return jsonObject.toString();
    }

    @RequestMapping(value="CreateOrders",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object createOrders(HttpSession session,HttpServletRequest request,int addressId){
        User user = (User)session.getAttribute("loginUser");
        if(request.getParameter("userId")!=null){
            int userId = Integer.parseInt(request.getParameter("userId"));
            user = userService.loadUser(userId);
        }
        Cart cart = userService.findCartByUserId(user.getId());
        List<OrderProduct> orderProducts = orderService.findOrderPorductByCartId(cart.getId());
        String[] orderProductId = new String[orderProducts.size()];
        for(int i=0;i<orderProducts.size();i++){
            orderProductId[i]=String.valueOf(orderProducts.get(i).getId());
        }
        Address address = addressService.findAddressById(addressId);
        OrderDetailPojo orderDetailPojo = orderService.addOrders(user.getId(),address,orderProductId);
        orderDetailPojo.setStatus("0");
        return  orderDetailPojo;
    }
}
