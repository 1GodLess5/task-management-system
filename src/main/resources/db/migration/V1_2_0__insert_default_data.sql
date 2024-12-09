-- id, name, email
INSERT INTO users VALUES
                      ('Jan Novák', 'jan.novak@gmail.com'),
                      ('Jakub Svoboda', 'jakub.svoboda@gmail.com');
-- id, user_id, name, description, created_at
INSERT INTO project VALUES
                        (1, 'Honzuv projekt', 'Tasky v praci', CURRENT_TIMESTAMP),
                        (2, 'Projekt Jakuba Svobody', 'Muj Todolist', CURRENT_TIMESTAMP);
-- id, user_id, project_id, name, description, status, created_at
INSERT INTO task VALUES
                     (1, 1, 'Vytvorit API', 'API ma byt pro noveho klienta', 'DONE', CURRENT_TIMESTAMP),
                     (1, 1, 'Otestovat API', 'Unit testy + integracni testy', 'NEW', CURRENT_TIMESTAMP),
                     (2, 2, 'Koupit mame darek', null, 'NEW', CURRENT_TIMESTAMP),
                     (2, null, 'Zavolat do skoly', 'Cislo mam na vizitce reditelky', 'NEW', CURRENT_TIMESTAMP);