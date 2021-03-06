package com.example.twitterclone.dao;

import com.example.twitterclone.entitites.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TweetDao extends AbstractDao<Tweet>{

    @Autowired
    TweetDao(EntityManager entityManager) {
        super(entityManager, Tweet.class, "tweet");
    }
}
