CREATE TABLE IF NOT EXISTS users (
                       id BIGSERIAL NOT NULL,
                       name varchar(45) NOT NULL,
                       email varchar(45) NOT NULL UNIQUE,
                       PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS project (
                         id BIGSERIAL NOT NULL,
                         user_id bigint NOT NULL,
                         name varchar(45) NOT NULL,
                         description varchar(160),
                         created_at datetime NOT NULL,
                         PRIMARY KEY (id),
                         CONSTRAINT  project_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS task (
                      id BIGSERIAL NOT NULL,
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