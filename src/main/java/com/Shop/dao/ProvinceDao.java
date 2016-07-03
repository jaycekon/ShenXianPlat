package com.Shop.dao;

import com.Shop.beans.Province;
import org.hibernate.Session;

/**
 * Created by Administrator on 2016/7/2.
 */
public class ProvinceDao extends  IBaseDao<Province>{

    public Province findByName(String name){
        Session session = super.openSession();
        String hql="from Province where name=:name";
        Province province = (Province) session.createQuery(hql).setParameter("name",name).uniqueResult();
        session.close();
        return province;
    }
}
