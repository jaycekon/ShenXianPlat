/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.dao;

import com.Shop.beans.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;


public class UserDao extends IBaseDao<User> {


    
    public void deleteUser(int id){
        User user =loadUser(id);
        this.openSession().delete(user);
    }
    
    public User loadUser(int id){
        String sql="from User where id=:id";
        User user = (User)this.openSession().createQuery(sql).setParameter("id", id).uniqueResult();
        return user;
    }

    
    public User loadUserByPhone(String phone){
        String sql="from User where phone = :phone";
        User user = (User)this.openSession().createQuery(sql).setParameter("phone",phone).uniqueResult();
        return user;
    }
    
    public User loginUser(String phone,String password){
        String sql="from User where phone=:phone";
        User user = (User)this.openSession().createQuery(sql).setParameter("phone",phone).uniqueResult();
        if(user!=null){
            if(user.getPassword().equals(password)||password==user.getPassword())return user;
        }
        return null; 
    }
    

}
