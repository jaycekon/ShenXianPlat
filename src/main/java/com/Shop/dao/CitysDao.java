package com.Shop.dao;

import com.Shop.beans.Citys;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class CitysDao extends IBaseDao<Citys> {

    public List<Citys> findByProvince(String pro){
        Session session = super.openSession();
        String hql="from Citys where pro=:pro";
        List<Citys> cityses = session.createQuery(hql).setParameter("pro",pro).list();
        session.close();
        return cityses;
    }

}
