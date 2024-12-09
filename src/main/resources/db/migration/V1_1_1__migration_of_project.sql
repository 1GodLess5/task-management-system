CREATE TABLE IF NOT EXISTS project (
                                       id bigint NOT NULL AUTO_INCREMENT,
                                       user_id bigint NOT NULL,
                                       name varchar(45) NOT NULL,
    description varchar(160),
    created_at datetime NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT  project_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
    );
