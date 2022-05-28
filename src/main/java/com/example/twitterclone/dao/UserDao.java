package com.example.twitterclone.dao;

import com.example.twitterclone.entitites.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of the DAO pattern, used to isolate the business logic from the
 * persistence mechanism. The class uses Hibernate to manipulate data from the database.
 * Make sure that you define the SessionFactory bean before using the class. The available
 * operations are: save, remove, update, get and get all.
 *
 * @author Rosca Maxim
 */

@Repository
public class UserDao implements IDao<User>{

    private final EntityManager entityManager;

    // Using the Spring Boot EntityManager here
    @Autowired
    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Function that returns a User object with all the information from
     * the database
     *
     * @param id the unique identifier of the object to be retrieved
     * @return the user object from the database or null
     */
    @Override
    @Transactional
    public User get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM user WHERE id = " + id, User.class);
        return query.getSingleResult();
    }

    /**
     * Function that returns a list of all the users from the database, if there are no
     * users returns an empty list
     *
     * @return return all the users from the database or empty list
     */
    @Override
    @Transactional
    public List<User> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM user", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    /**
     * Saves all the information of a user to the database, the id is not taken into
     * consideration, since it is set automatically. Check the database for values
     * that can be null to make sure there are no errors.
     *
     * @param user the user with all the information that needs to be added
     */
    @Override
    @Transactional
    public void save(@RequestBody User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(user);
    }

    /**
     * Update the information of a user from the database. Can't update one's id. Make sure
     * that the id from the provided object is a correct one, else the wrong object might
     * be edited.
     *
     * @param user the object with the updated information and the id
     */
    @Override
    @Transactional
    public void update(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
    }

    /**
     * Delete an object from the database. This operation is irreversible, make sure you delete
     * the right object.
     *
     * @param id the unique identifier of the object to be deleted
     */
    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.delete(get(id));
    }
}
