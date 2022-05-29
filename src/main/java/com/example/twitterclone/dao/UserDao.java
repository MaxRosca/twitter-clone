package com.example.twitterclone.dao;

import com.example.twitterclone.entitites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;


/**
 * Implementation of the DAO pattern, used to isolate the business logic from the
 * persistence mechanism. The class uses Hibernate to manipulate data from the database.
 * Make sure that you define the SessionFactory bean before using the class. The available
 * operations are: save, remove, update, get and get all.
 *
 * @author Rosca Maxim
 */

@Repository
public class UserDao extends AbstractDao<User>{

    // Using the Spring Boot EntityManager here
    @Autowired
    public UserDao(EntityManager entityManager) {
        super(entityManager, User.class, "user");
    }
}