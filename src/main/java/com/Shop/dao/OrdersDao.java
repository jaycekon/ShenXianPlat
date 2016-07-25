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
        session.close();
        return orderses;
    }
}
