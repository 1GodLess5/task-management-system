CREATE TABLE IF NOT EXISTS users (
                       id bigint NOT NULL AUTO_INCREMENT,
                       name varchar(45) NOT NULL,
                       email varchar(45) NOT NULL UNIQUE,
                       PRIMARY KEY (id)
);
