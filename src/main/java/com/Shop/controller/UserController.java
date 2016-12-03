/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.controller;

import com.Shop.DTO.AddressDto;
import com.Shop.DTO.UserDTO;
import com.Shop.Util.AddressPojo;
import com.Shop.Util.OrderDetailPojo;
import com.Shop.Util.OrdersPojo;
import com.Shop.beans.*;
import com.Shop.service.AddressService;
import com.Shop.service.OrderService;
import com.Shop.service.UserService;
import java.io.File;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

//    /**
//     * 添加用户
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String addUser(Model model) {
//        return "register";
//    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUser(User user) {
       JsonObject jsonObject = new JsonObject();
        System.out.println(user.getSex()+","+user.getNickname());
        if(user!=null){
            jsonObject = userService.addUser(user);
        }
        return jsonObject.toString();
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


//    /**
//     * 登录用户
//     *
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginUser(Model model) {
//        return "login";
//    }
//
//

    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object loginUser(User user, HttpServletRequest request,HttpSession session1,HttpServletResponse response) {

        HttpSession session = request.getSession();
        UserDTO u = userService.loginUser(user.getPhone(),user.getPassword());
        if(u.getUser()!=null) {
            Cart cart = userService.findCartByUserId(u.getUser().getId());
            if(cart!=null) {
                u.setCartCount(cart.getCount());
            }
            session.setAttribute("loginUser",u.getUser());
        }
        return u;
    }


    @RequestMapping(value = "/logOut", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object loginOut(HttpSession session){
        session.setAttribute("loginUser",null);
//        User u = (User)session.getAttribute("loginUser");
//        log.info(u.getPhone()+","+u.getPassword());
        JsonObject jsonObject = new JsonObject();
//        if(u!=null){
//            session.setAttribute("loginUser",null);
//        }else{
//            jsonObject.addProperty("status",1);
//            jsonObject.addProperty("hint","用户未登录");
//            return jsonObject.toString();
//        }
        jsonObject.addProperty("status",0);
        jsonObject.addProperty("hint","注销登录成功");
        return jsonObject.toString();
    }


    @RequestMapping(value = "/addAddress", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addAddress(Address address,HttpSession session,HttpServletRequest request){
        User loginUser = (User)session.getAttribute("loginUser");
        JsonObject jsonObject = new JsonObject();
        if(loginUser==null){
            if(request.getParameter("userId")!=null) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                loginUser = userService.loadUser(userId);
            }
        }
        if(loginUser ==null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("message","用户未登录");
            return jsonObject.toString();
        }
        address.setUserId(loginUser.getId());
        addressService.addAddress(address,loginUser.getId());
        jsonObject.addProperty("status",0);
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


    @RequestMapping(value = "/listAddress", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object listAddress(HttpSession session,HttpServletRequest request){
        User loginUser = (User)session.getAttribute("loginUser");
        JsonObject jsonObject = new JsonObject();
        if(loginUser ==null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("message","用户未登录");
            return jsonObject.toString();
        }
        AddressPojo addressPojo = new AddressPojo();
        List<Address> addresses = addressService.findAddressByUuserId(loginUser.getId());
        addressPojo.setAddresses(addresses);
        return addressPojo;
    }
    @RequestMapping(value = "/CreateOrder", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object createOrder(HttpSession session,int addressId,HttpServletRequest request){
        User loginUser = (User)session.getAttribute("loginUser");
//        User loginUser = userService.loadUser(1);
        JsonObject jsonObject = new JsonObject();
        if(loginUser ==null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("message","用户未登录");
            return jsonObject.toString();
        }
        String strs = request.getParameter("OrderProductId");
        String[] orderProductId = strs.split(",");
        for(String str:orderProductId){
            System.out.println(str);
        }
        Address address = addressService.findAddressById(addressId);
        OrderDetailPojo orderDetailPojo = orderService.addOrders(loginUser.getId(),address,orderProductId);
        return orderDetailPojo;
    }


    @RequestMapping(value = "/listOrders", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object listOrders(HttpSession session,HttpServletRequest request){
        User loginUser=(User)session.getAttribute("loginUser");
        JsonObject jsonObject = new JsonObject();
        if(session.getAttribute("loginUser")==null){
            int uId= Integer.parseInt(request.getParameter("userId"));
            loginUser = userService.loadUser(uId);
        }
        if(loginUser ==null){
            jsonObject.addProperty("status",false);
            jsonObject.addProperty("message","用户未登录");
            return jsonObject.toString();
        }
        List<OrderDetailPojo> orderses = orderService.findOrdersByUserId(loginUser.getId());
        OrdersPojo ordersPojo = new OrdersPojo();
        ordersPojo.setOrderses(orderses);
        return ordersPojo;
    }


    @RequestMapping(value="OrderDetail",method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object orderDetail(int id,HttpSession session,String phone,String password){
        User user = (User)session.getAttribute("loginUser");
        if(phone!=null){
            UserDTO userDTO = userService.loginUser(phone,password);
            if(userDTO.getUser()!=null){
                user =userDTO.getUser();
            }
        }
        if(user==null){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("errorMsg","用户未登陆");
            return jsonObject.toString();
        }

        OrderDetailPojo orderDetailPojo = orderService.findOrderDetail(id,user.getId());
        return orderDetailPojo;
    }

    @RequestMapping(value="updateAddress",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateAddress(Address address,HttpSession session,HttpServletRequest request){
        User user =(User)session.getAttribute("loginUser");
        if(user==null){
            if(request.getParameter("userId")!=null){
                int userId = Integer.parseInt(request.getParameter("userId"));
                user = userService.loadUser(userId);
            }
        }
        addressService.updateAddress(address,user.getId());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",0);
        jsonObject.addProperty("hint","修改收货地址成功!");
        return jsonObject.toString();
    }


    @RequestMapping(value="getAddress",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object getAddress(HttpSession session,HttpServletRequest request){
        User user=(User)session.getAttribute("loginUser");
        if(user==null){
            if(request.getParameter("userId")!=null){
                int userId = Integer.parseInt(request.getParameter("userId"));
                user= userService.loadUser(userId);
            }
        }
        Address address = addressService.findAddressByFlag(user.getId());
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress(address);
        return addressDto;
    }

    @RequestMapping(value="updateUser",method ={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateUser(User user,HttpSession session,HttpServletRequest request){
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser==null){
            if(request.getParameter("userId")!=null){
                int userId = Integer.parseInt(request.getParameter("userId"));
                loginUser = userService.loadUser(userId);
            }
        }
        JsonObject jsonObject = new JsonObject();
        if(loginUser==null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("message","用户未登录");
            return jsonObject.toString();
        }
//        user.setId(loginUser.getId());
        if(user.getPassword()!=null){
            loginUser.setPassword(user.getPassword());
        }
        if(user.getNickname()!=null){
            loginUser.setNickname(user.getNickname());
        }
        if(user.getBirthDay()!=null){
            loginUser.setBirthDay(user.getBirthDay());
        }
        if(user.getEmail()!=null){
            loginUser.setEmail(user.getEmail());
        }
        if(user.getSex()!=null){
            loginUser.setSex(user.getSex());
        }
        userService.updateUser(loginUser);
        session.setAttribute("loginUser",loginUser);
        jsonObject.addProperty("status",0);
        jsonObject.addProperty("message","修改成功");
        return jsonObject.toString();
    }

    @RequestMapping(value = "getUserMessage",method=RequestMethod.GET)
    @ResponseBody
    public Object getUserMessage(HttpSession session){
        if(session.getAttribute("loginUser")!=null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUser((User)session.getAttribute("loginUser"));
            return userDTO;
        }
        return null;
    }


}
