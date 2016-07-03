/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.controller;

import com.Shop.Util.AddressPojo;
import com.Shop.Util.OrdersPojo;
import com.Shop.beans.Address;
import com.Shop.beans.Citys;
import com.Shop.beans.Orders;
import com.Shop.beans.User;
import com.Shop.service.AddressService;
import com.Shop.service.OrderService;
import com.Shop.service.UserService;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    Logger log = Logger.getLogger(this.getClass());

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUser(User user) {
        JsonObject object = new JsonObject();
        object.addProperty("status",1);
        System.out.println(object.toString());
        if(user!=null){
            userService.addUser(user);
            object.addProperty("hint","添加用户成功");
        }
        return object.toString();
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
//       if(loginUser!=null){
//           if(loginUser.getType()==1)
        userService.deleteUser(id);
//       }
        return "redirect:addUser";
    }
    
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String index1(){
        log.info("123");
        System.out.println("123");
        return "extend";
    }


    /**
     * 登录用户
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(Model model) {
        return "login";
    }
    
    

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public User loginUser(Model model, User user,HttpSession session) {
        User u = userService.loginUser(user.getPhone(), user.getPassword());
        if (u != null) {
//            model.addAttribute("loginUser", u);
             session.setAttribute("loginUser", u);
        }
        return u;
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addAddress(Address address, Citys citys, HttpSession session){
//        User user = (User)session.getAttribute("loginUser");
        User user = userService.loadUser(1);
        JsonObject jsonObject = new JsonObject();
        if(user ==null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","用户未登录");
            return jsonObject.toString();
        }
        address.setUserId(user.getId());
        if(citys!=null)
        address.setArea(citys.getPro()+citys.getName());
        addressService.addAddress(address);
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteAddress(int id){
        String json = addressService.deleteAddress(id);
        return json;
    }


    @RequestMapping(value = "/addressDetail", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addressDetail(int id){
        Address address  = addressService.findAddressById(id);
        Gson gson =new Gson();
        return gson.toJson(address);
    }


    @RequestMapping(value = "/listAddress", method = RequestMethod.POST)
    @ResponseBody
    public AddressPojo listAddress(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        AddressPojo addressPojo = new AddressPojo();
        if(user ==null){
           return null;
        }
        List<Address> addresses = addressService.findAddressByUuserId(user.getId());
        addressPojo.setAddresses(addresses);
        return addressPojo;
    }
    @RequestMapping(value = "/CreateOrder", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String createOrder(HttpSession session,int addressId){
//        User user =(User)session.getAttribute("loginUser");
        User user = userService.loadUser(1);
        Address address = addressService.findAddressById(addressId);
        orderService.addOrders(user.getId(),address);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",true);
        return jsonObject.toString();
    }


    @RequestMapping(value = "/listOrders", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public OrdersPojo listOrders(HttpSession session){
        User user=(User)session.getAttribute("loginUser");
        if(user==null){
            return null;
        }
        List<Orders> orderses = orderService.findOrdersByUserId(user.getId());
        OrdersPojo ordersPojo = new OrdersPojo();
        ordersPojo.setOrderses(orderses);
        return ordersPojo;
    }

}
