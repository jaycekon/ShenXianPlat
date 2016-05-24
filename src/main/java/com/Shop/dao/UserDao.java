/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.dao;

import com.Shop.beans.User;
import com.Shop.utils.Pager;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author cjh
 */
public class UserDao extends BaseDao {

    public void addUser(User user){
//        System.out.println(user.getPhone());
        this.getSession().save(user);
    }
    
    public void deleteUser(int id){
        User user =loadUser(id);
        this.getSession().delete(user);
    }
    
    public User loadUser(int id){
        String sql="from User where id=:id";
        User user = (User)this.getSession().createQuery(sql).setParameter("id", id).uniqueResult();
        return user;
    }
    
    public void updateUser(User user){
        this.getSession().update(user);
    }
    
    public User loadUserByPhone(String phone){
        String sql="from User where phone = :phone";
        User user = (User)this.getSession().createQuery(sql).setParameter("phone",phone).uniqueResult();
        return user;
    }
    
    public User loginUser(String phone,String password){
        String sql="from User where phone=:phone";
        User user = (User)this.getSession().createQuery(sql).setParameter("phone",phone).uniqueResult();
        if(user!=null){
            if(user.getPassword().equals(password)||password==user.getPassword())return user;
        }
        return null; 
    }
    
    public Pager getAllUser(Pager pager){
        long count = (long) this.getSession().createQuery("select count(*) from User").uniqueResult();
        pager.setTotal(count);
        List list = this.getSession().createQuery("from User")
                .setFirstResult(pager.getOffset())
                .setMaxResults(pager.getMaxPageSize()).list();
        pager.setDatas(list);
        return pager;
    }
}
