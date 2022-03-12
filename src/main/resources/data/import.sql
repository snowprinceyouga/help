
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (1, 'admin', 'admin', 'admin', 'admin', 0);
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (2, 'support', 'support', 'support', 'support', 1);
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (3, 'user1', 'user1', 'user1', 'user1', 2);
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (4, 'user2', 'user2', 'user2', 'user2', 2);

INSERT INTO ticket (id, status, title, client_id, support_id) VALUES (5, 1, 'Hello user1', 3, 2);
INSERT INTO ticket (id, status, title, client_id, support_id) VALUES (6, 0, 'Title2', 3, NULL);
INSERT INTO ticket (id, status, title, client_id, support_id) VALUES (7, 0, 'Message user2', 4, NULL);

UPDATE hibernate_sequence SET next_val = 8 WHERE next_val = 1;
