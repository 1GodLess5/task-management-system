create sequence user_id_seq start with 1 increment by 1;
create sequence project_id_seq start with 1 increment by 1;
create sequence task_id_seq start with 1 increment by 1;

-- id, name, email
INSERT INTO users VALUES
                      (next value for user_id_seq, 'Jan Novák', 'jan.novak@gmail.com'),
                      (next value for user_id_seq, 'Jakub Svoboda', 'jakub.svoboda@gmail.com');
-- id, user_id, name, description, created_at
INSERT INTO project VALUES
                        (next value for project_id_seq, 1, 'Honzuv projekt', 'Tasky v praci', CURRENT_TIMESTAMP),
                        (next value for project_id_seq, 2, 'Projekt Jakuba Svobody', 'Muj Todolist', CURRENT_TIMESTAMP);
-- id, user_id, project_id, name, description, status, created_at
INSERT INTO task VALUES
                     (next value for task_id_seq, 1, 1, 'Vytvorit API', 'API ma byt pro noveho klienta', 'DONE', CURRENT_TIMESTAMP),
                     (next value for task_id_seq, 1, 1, 'Otestovat API', 'Unit testy + integracni testy', 'NEW', CURRENT_TIMESTAMP),
                     (next value for task_id_seq, 2, 2, 'Koupit mame darek', null, 'NEW', CURRENT_TIMESTAMP),
                     (next value for task_id_seq, 2, null, 'Zavolat do skoly', 'Cislo mam na vizitce reditelky', 'NEW', CURRENT_TIMESTAMP);