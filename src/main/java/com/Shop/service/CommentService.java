package com.Shop.service;

import com.Shop.DTO.CommentDto;
import com.Shop.beans.Comment;
import com.Shop.beans.User;
import com.Shop.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/7.
 */
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public void addComment(Comment comment,User user){
        comment.setCommentDate(new Date());
        comment.setUserId(user.getId());
        comment.setUserImage(user.getImg());
        comment.setUsername(user.getNickname());
        commentDao.addAnyType(comment);
    }


    public List<Comment> findCommentByUserId(int userId){

        return commentDao.findCommentByUserId(userId);
    }

    public List<CommentDto> findCommentByGoodId(int goodId){
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments =commentDao.findCommentByGoodId(goodId);
        for(Comment comment:comments){
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    public List<Comment> findAllComment(){
        return commentDao.findAll("Comment");
    }

    public void deleteComment(int commentId){
        Comment comment = commentDao.findById(commentId,"Comment");
        commentDao.deleteAnyType(comment);
    }
}
