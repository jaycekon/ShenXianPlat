package com.Shop.dao;

import com.Shop.beans.Product;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ProductDao extends IBaseDao<Product> {

        public List<Product> findProductByIndex(){
            Session session = super.openSession();
            String hql= "from Product where indes = 1";
            List<Product> products = session.createQuery(hql).list();
            return products;
        }

        public List<Product> findProductByCate(String cate){
            Session session = super.openSession();
            String hql = "from Product where cate like:cate";
            List<Product> products = session.createQuery(hql).setParameter("cate","%"+cate+"%").list();
            return products;
        }


}
