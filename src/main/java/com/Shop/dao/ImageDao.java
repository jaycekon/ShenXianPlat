package com.Shop.dao;

import com.Shop.beans.Image;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ImageDao extends IBaseDao<Image> {
    public List<Image> findByProductId(int productId){
        Session session = super.openSession();
        String hql = "from Image where productId=:productId";
        List<Image> images = session.createQuery(hql).setParameter("productId",productId).list();
        return images;
    }
}
