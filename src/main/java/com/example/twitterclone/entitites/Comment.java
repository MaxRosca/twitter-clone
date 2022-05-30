package com.example.twitterclone.entitites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity(name="comment")
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @Getter @Setter private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="author")
    @JsonBackReference
    @Getter @Setter User author;

    @Column(name="content")
    @Getter @Setter String content;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="tweet")
    @JsonBackReference
    @Getter @Setter Tweet tweet;

    @ManyToMany(fetch=FetchType.LAZY ,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="comment_like",
            joinColumns = @JoinColumn(name="tweet"),
            inverseJoinColumns = @JoinColumn(name = "user"))
    @JsonBackReference
    @Getter @Setter private List<User> likes;
}
