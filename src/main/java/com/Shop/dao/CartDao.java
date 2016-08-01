package com.Shop.dao;

import com.Shop.beans.Cart;
import org.hibernate.Session;

/**
 * Created by Administrator on 2016/6/28.
 */
public class CartDao extends IBaseDao<Cart> {
//    public Cart findById(int id){
//        String hql="from Cart where id=:id";
//        Cart cart =(Cart)super.getSession().createQuery(hql).setParameter("id",id).uniqueResult();
//        return cart;
//    }

    public Cart findByUserId(int id){
        Session session = super.openSession();
        String hql="from Cart where userId=:id";
        Cart cart = (Cart)session.createQuery(hql).setParameter("id",id).uniqueResult();
        return cart;
    }
}
