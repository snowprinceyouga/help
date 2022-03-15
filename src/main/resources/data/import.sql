
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (1, 'admin', 'admin', 'admin', 'admin', 0);
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (2, 'support', 'support', 'support', 'support', 1);
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (3, 'user1', 'user1', 'user1', 'user1', 2);
INSERT INTO user (id, first_name, last_name, login, password, role) VALUES (4, 'user2', 'user2', 'user2', 'user2', 2);

INSERT INTO ticket (id, status, title, client_id, support_id) VALUES (1, 1, 'Hello user1',1, 2);
INSERT INTO ticket (id, status, title, client_id, support_id) VALUES (2, 0, 'Title2', 1, 2);
INSERT INTO ticket (id, status, title, client_id, support_id) VALUES (3, 0, 'Message user2', 4, NULL);

INSERT INTO comment (id, message, ticket_id, user_id) VALUES (1, "comment ticket1", 1, 3);
INSERT INTO comment (id, message, ticket_id, user_id) VALUES (2, "comment ticket2", 2, 3);
INSERT INTO comment (id, message, ticket_id, user_id) VALUES (3, "comment ticket11", 1, 2);
INSERT INTO comment (id, message, ticket_id, user_id) VALUES (4, "comment ticket111", 1, 2);
INSERT INTO comment (id, message, ticket_id, user_id) VALUES (5, "comment ticket22", 2, 2);
INSERT INTO comment (id, message, ticket_id, user_id) VALUES (6, "comment ticket3", 3, 4);

UPDATE hibernate_sequence SET next_val = 8 WHERE next_val = 1;
