UPDATE users
SET name = 'Jan Novák'
WHERE id = 1;

UPDATE project
SET name = 'Honzův projekt', description = 'Tasky v práci'
WHERE id = 1;
UPDATE project
SET description = 'Můj Todolist'
WHERE id = 2;

UPDATE task
SET name = 'Vytvořit API', description = 'API má být pro nového klienta'
WHERE id = 1;
UPDATE task
SET description = 'Unit testy + integrační testy'
WHERE id = 2;
UPDATE task
SET name = 'Koupit mamce dárek'
WHERE id = 3;
UPDATE task
SET name = 'Zavolat do školy', description = 'Číslo mám na vizitce ředitelky'
WHERE id = 1;
