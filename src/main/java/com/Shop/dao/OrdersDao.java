package com.Shop.dao;

import com.Shop.beans.Orders;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class OrdersDao extends IBaseDao<Orders> {

    public List<Orders> findOrdersByUserId(int userId){
        Session session = super.openSession();
        String hql="from Orders where userId=:userId order by setDate";
        List<Orders> orderses = session.createQuery(hql).setParameter("userId",userId).list();
        return orderses;
    }

    public Orders findOrderByIdAndUserId(int id,int userId){
        Session session =super.openSession();
        String hql="from Orders where id=:id and userId=:userId";
        Orders orders = (Orders)session.createQuery(hql).setParameter("id",id).setParameter("userId",userId).uniqueResult();
        return orders;
    }
}
