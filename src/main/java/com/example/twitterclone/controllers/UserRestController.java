package com.example.twitterclone.controllers;

import com.example.twitterclone.dao.UserDao;
import com.example.twitterclone.entitites.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for manipulating user data. 
 *
 * @author Rosca Maxim
 */

@RestController
@RequestMapping("/api/user/")
public class UserRestController {

    final UserDao dao;

    public UserRestController(UserDao dao) {
        this.dao = dao;
    }

    /**
     * Function mapped to the get request to "/api/user/". Returns all the user
     * as a json format.
     *
     * @return the json format of all users from database
     */
    @GetMapping("/")
    @ResponseBody
    List<User> getUsers() {
        return dao.getAll();
    }

    /**
     * Function mapped to get request to "/api/user/{id}". Returns the information
     * about the user with the specific id in json format.
     *
     * @param id the id of the user
     * @return the information about the user with the provided id as json
     */
    @GetMapping("/{id}")
    @ResponseBody
    User getUser(@PathVariable(value="id") Integer id) {
        return dao.get(id);
    }

    /**
     * Function mapped to the delete request to "/api/user/{id}". Deletes the user
     * with the provided id from the database. Must be used carefully the action can't
     * be undone.
     *
     * @param id the id of the user to be deleted
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    void deleteUser(@PathVariable(value="id") Integer id) {
        dao.delete(id);
    }

    /**
     * Function mapped to put request to "/api/user/add". The request body must be the
     * json representation object of the user object that needs to be added. Only the id
     * can be null
     *
     * @param user the user that needs to be added
     */
    @PutMapping("/add")
    @ResponseBody
    void addUser(@RequestBody User user) {
        user.setId(null);
        dao.save(user);
    }

    /**
     * Function mapped to post request to "/api/user/update". The request body must be the
     * json representation of the updated user object. The id must correspond to the one from
     * the database.
     *
     * @param user the object with the updated information
     */
    @PostMapping("/update")
    @ResponseBody
    void updateUser(@RequestBody User user) {
        dao.update(user);
    }
}
