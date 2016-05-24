/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.service;

import com.Shop.beans.User;
import com.Shop.dao.UserDao;
import com.Shop.utils.Pager;

/**
 *
 * @author cjh
 */
public class UserService {

    private UserDao userDao;

    
    
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    /* ************************************************************************
     *
     * 下面开始写UserService的代码
     *
     *************************************************************************/
    public void addUser(User user){
       userDao.addUser(user);
    }
    
    /**
     * 处理用户登录
     *
     * @param password 用户密码
     * @return 登陆成功后用户的实例
     */
    
    public User loginUser(String phone,String password){
        User user=userDao.loginUser(phone, password);
        if(user==null){
            System.out.println("用户密码不正确");
            return null;
        }
        return user;
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
        userDao.updateUser(user);
    }
    
    public Pager listUser(Pager pager){
        return userDao.getAllUser(pager);
    }
    

}
