DROP TABLE IF EXISTS commentlike;

DROP TABLE IF EXISTS comments;

DROP TABLE IF EXISTS tweets;

DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id int NOT NULL UNIQUE PRIMARY KEY auto_increment,
    firstName varchar(255) NOT NULL,
    lastName varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    username varchar(255) NOT NULL UNIQUE
);

CREATE TABLE tweets(
    id int NOT NULL UNIQUE PRIMARY KEY auto_increment,
    content varchar(255) NOT NULL,
    author int NOT NULL,
    FOREIGN KEY (author) REFERENCES users(id)
);

CREATE TABLE comments(
    id int NOT NULL UNIQUE PRIMARY KEY auto_increment,
    author int NOT NULL,
    content varchar(255) NOT NULL,
    createDate date NOT NULL,
    tweet int NOT NULL,
    FOREIGN KEY (author) REFERENCES users(id),
    FOREIGN KEY (tweet) REFERENCES tweets(id)
);

CREATE TABLE commentlike(
    user int NOT NULL,
    tweet int NOT NULL,
    FOREIGN KEY (user) REFERENCES users(id),
    FOREIGN KEY (tweet) REFERENCES tweets(id)
);
