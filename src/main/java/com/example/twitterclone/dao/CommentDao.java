package com.example.twitterclone.dao;

import com.example.twitterclone.entitites.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommentDao extends AbstractDao<Comment>{

    @Autowired
    CommentDao(EntityManager entityManager) {
        super(entityManager, Comment.class, "comment");
    }
}
