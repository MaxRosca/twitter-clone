package com.example.twitterclone.dao;

import com.example.twitterclone.entitites.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class TweetDao extends AbstractDao<Tweet>{
    @Autowired
    TweetDao(EntityManager entityManager) {
        super(entityManager, Tweet.class, "tweet");
    }
}
