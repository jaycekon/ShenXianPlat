package com.Shop.dao;

import com.Shop.beans.Cate;
import org.hibernate.Session;

/**
 * Created by Administrator on 2016/6/28.
 */
public class CateDao extends IBaseDao<Cate> {
    public Cate findByCateName(String name){
        Session session = super.openSession();
        String hql="from Cate where name=:name";
        Cate cate = (Cate)session.createQuery(hql).setParameter("name",name).uniqueResult();
        session.close();
        return cate;
    }

    public Cate findByCateNameAndId(String name,int id){
        Session session = super.openSession();
        String hql="from Cate where name=:name and cate_id=:id";
        Cate cate = (Cate)session.createQuery(hql).setParameter("name",name).setParameter("id",id).uniqueResult();
        session.close();
        return cate;
    }
}
