package com.example.twitterclone.controllers;

import com.example.twitterclone.dao.TweetDao;
import com.example.twitterclone.dao.UserDao;
import com.example.twitterclone.entitites.Tweet;
import com.example.twitterclone.entitites.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for manipulating tweet data.
 *
 * @author Rosca Maxim
 */

@RestController
@RequestMapping("/api/tweet")
public class TweetRestController {

    final TweetDao dao;

    public TweetRestController(TweetDao dao) {
        this.dao = dao;
    }

    /**
     * Function mapped to the get request to "/api/tweet/". Returns all the tweets
     * as a json format.
     *
     * @return the json format of all users from database
     */
    @GetMapping("")
    List<Tweet> getTweets() {
        return dao.getAll();
    }

    /**
     * Function mapped to get request to "/api/tweet/{id}". Returns the information
     * about the tweet with the specific id in json format.
     *
     * @param id the id of the tweet
     * @return the information about the tweet with the provided id as json
     */
    @GetMapping("/{id}")
    @ResponseBody
    Tweet getTweet(@PathVariable(value="id") Integer id) {
        return dao.get(id);
    }

    /**
     * Function mapped to the delete request to "/api/tweet/{id}". Deletes the tweet
     * with the provided id from the database. Must be used carefully the action can't
     * be undone.
     *
     * @param id the id of the tweet to be deleted
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    void deleteTweet(@PathVariable(value="id") Integer id) {
        dao.delete(id);
    }

    /**
     * Function mapped to put request to "/api/tweet". The request body must be the
     * json representation object of the tweet object that needs to be added. Only the id
     * can be null
     *
     * @param tweet the tweet that needs to be added
     */
    @PutMapping("")
    @ResponseBody
    void addUser(@RequestBody Tweet tweet) {
        tweet.setId(null);
        dao.save(tweet);
    }

    /**
     * Function mapped to post request to "/api/tweet". The request body must be the
     * json representation of the updated tweet object. The id must correspond to the one from
     * the database.
     *
     * @param tweet the object with the updated information
     */
    @PostMapping("")
    @ResponseBody
    void updateUser(@RequestBody Tweet tweet) {
        dao.update(tweet);
    }
}
