package com.Shop.dao;

import com.Shop.beans.OrderProduct;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class OrderProductDao extends IBaseDao<OrderProduct> {
//    public OrderProduct findById(int id){
//        String hql="from OrderProduct where id=:id";
//        OrderProduct orderProduct = (OrderProduct)super.getSession().createQuery(hql).setParameter("id",id).uniqueResult();
//        return orderProduct;
//    }


    public OrderProduct findByCartIdAndProductId(int cartId,int productId){
        Session session =super.openSession();
        String hql="from OrderProduct where cartId=:cartId and productId=:productId";
        OrderProduct orderProduct = (OrderProduct)session.createQuery(hql)
                .setParameter("cartId",cartId).setParameter("productId",productId).uniqueResult();
        session.close();
        return orderProduct;
    }

    public List<OrderProduct>  findByCartId(int cartId){
        Session session =super.openSession();
        String hql="from OrderProduct where cartId=:cartId";
        List<OrderProduct> orderProducts = session.createQuery(hql).setParameter("cartId",cartId).list();
        session.close();
        return orderProducts;
    }

    public List<OrderProduct>  findByOrderId(int orderId){
        Session session =super.openSession();
        String hql="from OrderProduct where orderId=:orderId";
        List<OrderProduct> orderProducts = session.createQuery(hql).setParameter("orderId",orderId).list();
        session.close();
        return orderProducts;
    }

}
