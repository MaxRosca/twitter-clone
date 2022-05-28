package com.example.twitterclone.dao;

import com.example.twitterclone.entitites.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementation of the DAO pattern, used to isolate the business logic from the
 * persistence mechanism. The class uses Hibernate to manipulate data from the database.
 * Make sure that you define the SessionFactory bean before using the class. The available
 * operations are: save, remove, update, get and get all.
 *
 * @author Rosca Maxim
 */

public class UserDao implements IDao<User>{

    // Make sure to define the bean for the SessionFactory
    @Autowired
    private SessionFactory sessionFactory;
    private List<User> users;

    /**
     * Function that returns a User object with all the information from
     * the database
     *
     * @param id the unique identifier of the object to be retrieved
     * @return the user object from the database or null
     */
    @Override
    public User get(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("FROM users WHERE id = " + id, User.class);
        return query.getSingleResult();
    }

    /**
     * Function that returns a list of all the users from the database, if there are no
     * users returns an empty list
     *
     * @return return all the users from the database or empty list
     */
    @Override
    public List<User> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("FROM users", User.class);
        this.users = query.getResultList();
        return this.users;
    }

    /**
     * Saves all the information of a user to the database, the id is not taken into
     * consideration, since it is set automatically. Check the database for values
     * that can be null to make sure there are no errors.
     *
     * @param user the user with all the information that needs to be added
     */
    @Override
    public void save(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
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
    public void update(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

    /**
     * Delete an object from the database. This operation is irreversible, make sure you delete
     * the right object.
     *
     * @param id the unique identifier of the object to be deleted
     */
    @Override
    public void delete(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(get(id));
    }
}
