CREATE TABLE users
(
    user     varchar(128) NOT NULL,
    password varchar(128) DEFAULT NULL,
    PRIMARY KEY (user)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
