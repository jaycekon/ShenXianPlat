package com.Shop.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class IBaseDao<T> extends BaseDao {
    public void addAnyType(T t){
        super.openSession().save(t);
    }

    public void addOrUpdate(T t){
        super.openSession().saveOrUpdate(t);
    }

    public void deleteAnyType(T t){
        Session session =super.openSession();
        session.delete(t);
    }

    public void updateAnyType(T t){
        Session session =super.openSession();
        System.out.println("更新");
        session.update(t);
    }

    public List findAll(String type){
        String hql="from "+type;
        Session session =super.openSession();
        List list = session.createQuery(hql).list();
        return list;
    }

    public T findById(int id,String anyType){
        Session session =super.openSession();
        String hql="from "+anyType+" where id=:id";
        T t = (T)session.createQuery(hql).setParameter("id",id).uniqueResult();
        return t;
    }
}
