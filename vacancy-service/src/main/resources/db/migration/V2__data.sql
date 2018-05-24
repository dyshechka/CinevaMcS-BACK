INSERT INTO duty (name) VALUES ('Уборка');
INSERT INTO duty (name) VALUES ('Продажа билетов');
INSERT INTO duty (name) VALUES ('Контроль билетов');

INSERT INTO requirement (name) VALUES ('Вежливость');
INSERT INTO requirement (name) VALUES ('Аккуратность');
INSERT INTO requirement (name) VALUES ('Исполнительность');

INSERT INTO status (name) VALUES ('Открыта');
INSERT INTO status (name) VALUES ('Закрыта');

INSERT INTO vacancy (name, schedule, work_experience, salary) VALUES ('Уборщик', '2x2', 'Без опыта', 20000.00);
INSERT INTO vacancy (name, schedule, work_experience, salary) VALUES ('Кассир', '2x2', '2 года', 25000.00);
INSERT INTO vacancy (name, schedule, work_experience, salary) VALUES ('Контроллёр', '2x2', '1 год', 27000.00);

INSERT INTO status_vacancy (id_status, id_vacancy) VALUES (1, 1);
INSERT INTO status_vacancy (id_status, id_vacancy) VALUES (1, 2);
INSERT INTO status_vacancy (id_status, id_vacancy) VALUES (1, 3);

INSERT INTO requirement_vacancy (id_requirement, id_vacancy) VALUES (3, 1);
INSERT INTO requirement_vacancy (id_requirement, id_vacancy) VALUES (2, 1);
INSERT INTO requirement_vacancy (id_requirement, id_vacancy) VALUES (1, 2);
INSERT INTO requirement_vacancy (id_requirement, id_vacancy) VALUES (2, 2);
INSERT INTO requirement_vacancy (id_requirement, id_vacancy) VALUES (3, 3);
INSERT INTO requirement_vacancy (id_requirement, id_vacancy) VALUES (1, 3);

INSERT INTO duty_vacancy (id_duty, id_vacancy) VALUES (1, 1);
INSERT INTO duty_vacancy (id_duty, id_vacancy) VALUES (2, 2);
INSERT INTO duty_vacancy (id_duty, id_vacancy) VALUES (3, 3);

