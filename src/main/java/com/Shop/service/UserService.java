/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.service;

import com.Shop.DTO.UserDTO;
import com.Shop.beans.Cart;
import com.Shop.beans.User;
import com.Shop.dao.CartDao;
import com.Shop.dao.UserDao;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;
    Logger log = Logger.getLogger(this.getClass());



    /* ************************************************************************
     *
     * 下面开始写UserService的代码
     *
     *************************************************************************/
    public JsonObject addUser(User user){
       User u = userDao.loadUserByPhone(user.getPhone());
        JsonObject jsonObject= new JsonObject();
        if(u!=null){
            jsonObject.addProperty("status",1);
            jsonObject.addProperty("hint","用户已存在");
            return jsonObject;
        }
       userDao.addAnyType(user);
        jsonObject.addProperty("status",0);
        jsonObject.addProperty("hint","成功添加用户");
        return jsonObject;
    }
    
    /**
     * 处理用户登录
     *
     * @param password 用户密码
     * @return 登陆成功后用户的实例
     */
    
    public UserDTO loginUser(String phone,String password){
        User user=userDao.loginUser(phone);
        UserDTO userDTO = new UserDTO();
        if(user==null){
            userDTO.setState(1);
            userDTO.setErrorMsg("账号错误，找不到用户");
        }else if(!user.getPassword().equals(password)){
            userDTO.setState(1);
            userDTO.setErrorMsg("密码错误");
        }else{
            log.info("=====================>"+user.getPhone()+","+user.getPassword());
            userDTO.setUser(user);
        }
        return userDTO;
    }
    
    public User loadUser(int id){
        User user =userDao.loadUser(id);
        if(user==null){
            System.out.println("用户不存在!");
            return null;
        }
        return user;
    }
    
    public User loadUserByPhone(String phone){
        User user =userDao.loadUserByPhone(phone);
        if(user==null){
            System.out.println("用户不存在!");
            return null;
        }
        return user;
    }
    
    public void deleteUser(int id){
        userDao.deleteUser(id);
    }
    
    public void updateUser(User user){
        userDao.updateAnyType(user);
    }
    

    public Cart findCartByUserId(int userId){
        return cartDao.findByUserId(userId);
    }

}
