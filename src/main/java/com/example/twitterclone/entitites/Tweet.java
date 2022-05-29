package com.example.twitterclone.entitites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @Getter @Setter Long id;

    @Column(name="content")
    @Getter @Setter String content;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="author")
    @JsonBackReference
    @Getter @Setter User author;

    public Tweet(String content, User author) {
        this.content = content;
        this.author = author;
    }

    public Tweet() {}

}
