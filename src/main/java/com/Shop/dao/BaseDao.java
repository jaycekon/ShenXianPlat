/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author cjh
 */
public class BaseDao {
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    protected Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
}
