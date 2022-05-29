package com.example.twitterclone.entitites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * User entity class connected to database with Hibernate
 *
 * @author Rosca Maxim
 */

@Entity(name="user")
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @Getter @Setter private Integer id;

    @Column(name="first_name")
    @Getter @Setter private String firstName;

    @Column(name="last_name")
    @Getter @Setter private String lastName;

    @Column(name="email")
    @Getter @Setter private String email;

    @Column(name="username")
    @Getter @Setter private String username;

    @OneToMany(mappedBy="author", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    @Getter @Setter private List<Tweet> tweets;

    @OneToMany(mappedBy="author", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    @Getter @Setter private List<Comment> commentList;

    @ManyToMany(fetch=FetchType.LAZY ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="comment_like",
            joinColumns = @JoinColumn(name="user"),
            inverseJoinColumns = @JoinColumn(name = "tweet"))
    @JsonBackReference
    @Getter @Setter private List<Comment> likedComments;

    public User() {}

    public User(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }

    /**
     * Function to add a tweet to the tweets list. Also, it sets the tweets author to this user.
     *
     * @param tweet the tweet to be added to the list
     */
    public void addTweet(Tweet tweet) {
        this.tweets.add(tweet);
        tweet.setAuthor(this);
    }

    /**
     * Function to add a comment to the comments list. Also, it sets the comment's author to this user.
     *
     * @param comment the comment to be added to the list
     */
    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.setAuthor(this);
    }

}
