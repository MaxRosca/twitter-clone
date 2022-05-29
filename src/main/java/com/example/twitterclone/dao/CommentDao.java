package com.example.twitterclone.dao;

import com.example.twitterclone.entitites.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class CommentDao extends AbstractDao<Comment>{

    @Autowired
    CommentDao(EntityManager entityManager) {
        super(entityManager, Comment.class, "comment");
    }
}
