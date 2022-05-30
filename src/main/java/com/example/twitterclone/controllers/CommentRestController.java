package com.example.twitterclone.controllers;

import com.example.twitterclone.dao.CommentDao;
import com.example.twitterclone.entitites.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for manipulating comment data.
 *
 * @author Rosca Maxim
 */

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    final CommentDao dao;

    public CommentRestController(CommentDao dao) {
        this.dao = dao;
    }

    /**
     * Function mapped to the get request to "/api/comment/". Returns all the comments
     * as a json format.
     *
     * @return the json format of all users from database
     */
    @GetMapping("")
    List<Comment> getComments() {
        return dao.getAll();
    }

    /**
     * Function mapped to get request to "/api/comment/{id}". Returns the information
     * about the comment with the specific id in json format.
     *
     * @param id the id of the comment
     * @return the information about the comment with the provided id as json
     */
    @GetMapping("/{id}")
    @ResponseBody
    Comment getComment(@PathVariable(value="id") Integer id) {
        return dao.get(id);
    }

    /**
     * Function mapped to the delete request to "/api/comment/{id}". Deletes the comment
     * with the provided id from the database. Must be used carefully the action can't
     * be undone.
     *
     * @param id the id of the comment to be deleted
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    void deleteComment(@PathVariable(value="id") Integer id) {
        dao.delete(id);
    }

    /**
     * Function mapped to put request to "/api/comment". The request body must be the
     * json representation object of the comment object that needs to be added. Only the id
     * can be null
     *
     * @param comment the comment that needs to be added
     */
    @PutMapping("")
    @ResponseBody
    void addUser(@RequestBody Comment comment) {
        comment.setId(null);
        dao.save(comment);
    }

    /**
     * Function mapped to post request to "/api/comment". The request body must be the
     * json representation of the updated comment object. The id must correspond to the one from
     * the database.
     *
     * @param comment the object with the updated information
     */
    @PostMapping("")
    @ResponseBody
    void updateUser(@RequestBody Comment comment) {
        dao.update(comment);
    }
}
