CREATE TABLE IF NOT EXISTS task (
                                    id bigint NOT NULL AUTO_INCREMENT,
                                    user_id bigint NOT NULL,
                                    project_id bigint,
                                    name varchar(45) NOT NULL,
    description varchar(160),
    status varchar(10) NOT NULL,
    created_at datetime NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT task_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT task_project_id_fk FOREIGN KEY (project_id) REFERENCES project (id)
    );
