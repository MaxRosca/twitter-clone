package com.example.twitterclone.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Interface to ease the implementation of Data Access Object principle.
 * Unlike just accessing the database from the servlet class, for instance,
 * this pattern provides isolation from the application layer for the persistence
 * layer. It is used to manipulate data from any persistence mechanism. The user
 * can get and delete the object based on its unique identifier and also save
 * or update a certain object by providing an instance of it. There are already
 * provided default implementations to reduce code smell.
 *
 * @author Rosca Maxim
 * @param <T> the type of object that is being manipulated
 */

abstract class AbstractDao<T> {

    private final EntityManager entityManager;
    // This variable is required because using .class on type parameter is not allowed
    // because of type erasure
    private final Class<T> tClass;
    // The table name of the table to be edited
    private final String tableName;

    /**
     * Constructor for the dao class
     *
     * @param entityManager the spring entity manager, use autowired
     * @param tClass the class of T, required because of type erasure
     * @param tableName the name of the table from the database which is edited
     */
    AbstractDao(EntityManager entityManager, Class<T> tClass, String tableName) {
        this.entityManager = entityManager;
        this.tClass = tClass;
        this.tableName = tableName;
    }

    /**
     * Return an object from the persistence mechanism based on its unique identifier.
     * It is recommended that the id parameter is unique.
     *
     * @param id the unique identifier of the object to be retrieved
     * @return the object retrieved from the storage place
     */
    @Transactional
    public T get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            Query<T> query = currentSession.createQuery("FROM " + tableName + " WHERE id = " + id, tClass);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Returns all the objects from the persistence mechanism of type T
     *
     * @return a list of all the objects from the persistence mechanism of type T
     */
    @Transactional
    public List<T> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<T> query = currentSession.createQuery("FROM " + tableName, tClass);
        List<T> users = query.getResultList();
        return users;
    }

    /**
     * Takes an instance of the object and stores it to the persistence mechanism
     *
     * @param t the object that needs to be saved
     */
    @Transactional
    public void save(T t) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(t);
    }

    /**
     * Update an object from the persistence mechanism. It is recommended that the
     * unique identifier in the instance object is not null and corresponds to the
     * one stored. Otherwise, it is it will be hard to understand which object needs
     * to be updated.
     *
     * @param t the object with the updated information and the unique identifier present
     */
    @Transactional
    public void update(T t) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(t);
    }

    /**
     * Delete an object from the persistence mechanism based on its unique identifier.
     *
     * @param id the unique identifier of the object to be deleted
     */
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.delete(get(id));
    }

    /**
     * Return the number of rows in the table
     *
     * @return the number of rows in the table
     */
    @Transactional
    public Long count() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Long> query = currentSession.createQuery("SELECT count(*) FROM " + tableName, Long.class);
        Long count = query.uniqueResult();
        return count;
    }

}
