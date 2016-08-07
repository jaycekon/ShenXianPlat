package com.Shop.dao;

import com.Shop.beans.Comment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/7.
 */
public class CommentDao extends IBaseDao<Comment> {
    public List<Comment> findCommentByGoodId(int goodId){
        String hql="from Comment where goodId=:goodId order by commentDate desc ";
        List<Comment> comments = super.openSession().createQuery(hql).setParameter("goodId",goodId).list();
        return comments;
    }

    public List<Comment> findCommentByUserId(int userId){
        String hql="from Comment where userId=:userId  order by commentDate desc ";
        List<Comment> comments = super.openSession().createQuery(hql).setParameter("userId",userId).list();
        return comments;
    }
}
