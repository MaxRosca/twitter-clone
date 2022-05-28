package com.example.twitterclone.entitites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    public User() {}

    public User(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }
}
