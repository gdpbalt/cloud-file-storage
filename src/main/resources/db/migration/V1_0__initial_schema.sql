CREATE TABLE users
(
    id       bigint(20)   NOT NULL AUTO_INCREMENT,
    username varchar(128) NOT NULL UNIQUE,
    password varchar(128) NOT NULL,
    active   bit(1)       NOT NULL,
    created  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
