package com.Shop.dao;

import com.Shop.beans.Address;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class AddressDao extends IBaseDao<Address> {
    public List<Address> findAddressByUserId(int userId){
        Session session  = super.openSession();
        String hql= "from Address where userId=:userId";
        List<Address> addresses = session.createQuery(hql).setParameter("userId",userId).list();
        return addresses;
    }

    public Address findAddressByFlag(int flag,int userId){
        Session session  = super.openSession();
        String hql= "from Address where flag=:flag and userId=:userId";
        Address addresses = (Address)session.createQuery(hql).setParameter("flag",flag).setParameter("userId",userId).uniqueResult();
        return addresses;
    }
}
