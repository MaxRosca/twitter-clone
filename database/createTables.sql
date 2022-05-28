DROP TABLE IF EXISTS commentlike;

DROP TABLE IF EXISTS comment;

DROP TABLE IF EXISTS tweet;

DROP TABLE IF EXISTS user;

CREATE TABLE user(
    id int NOT NULL UNIQUE PRIMARY KEY auto_increment,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    username varchar(255) NOT NULL UNIQUE
);

CREATE TABLE tweet(
    id int NOT NULL UNIQUE PRIMARY KEY auto_increment,
    content varchar(255) NOT NULL,
    author int NOT NULL,
    FOREIGN KEY (author) REFERENCES user(id)
);

CREATE TABLE comment(
    id int NOT NULL UNIQUE PRIMARY KEY auto_increment,
    author int NOT NULL,
    content varchar(255) NOT NULL,
    tweet int NOT NULL,
    FOREIGN KEY (author) REFERENCES user(id),
    FOREIGN KEY (tweet) REFERENCES tweet(id)
);

CREATE TABLE commentlike(
    user int NOT NULL,
    tweet int NOT NULL,
    FOREIGN KEY (user) REFERENCES user(id),
    FOREIGN KEY (tweet) REFERENCES tweet(id)
);
