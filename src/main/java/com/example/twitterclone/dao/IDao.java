package com.example.twitterclone.dao;

import java.util.List;

/**
 * Interface to ease the implementation of Data Access Object principle.
 * Unlike just accessing the database from the servlet class, for instance,
 * this pattern provides isolation from the application layer for the persistence
 * layer. It is used to manipulate data from any persistence mechanism. The user
 * can get and delete the object based on its unique identifier and also save
 * or update a certain object by providing an instance of it.
 *
 * @author Rosca Maxim
 * @param <T> the type of object that is being manipulated
 */

public interface IDao<T> {

    /**
     * Return an object from the persistence mechanism based on its unique identifier.
     * It is recommended that the id parameter is unique.
     *
     * @param id the unique identifier of the object to be retrieved
     * @return the object retrieved from the storage place
     */
    T get(int id);

    /**
     * Returns all the objects from the persistence mechanism of type T
     *
     * @return a list of all the objects from the persistence mechanism of type T
     */
    List<T> getAll();

    /**
     * Takes an instance of the object and stores it to the persistence mechanism
     *
     * @param t the object that needs to be saved
     */
    void save(T t);

    /**
     * Update an object from the persistence mechanism. It is recommended that the
     * unique identifier in the instance object is not null and corresponds to the
     * one stored. Otherwise, it is it will be hard to understand which object needs
     * to be updated.
     *
     * @param t the object with the updated information and the unique identifier present
     */
    void update(T t);

    /**
     * Delete an object from the persistence mechanism based on it's unique identifier.
     *
     * @param id the unique identifier of the object to be deleted
     */
    void delete(int id);

}
