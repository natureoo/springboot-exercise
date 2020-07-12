INSERT INTO user (id, username, password, name, age) VALUES (1, 'tom', '123456', 'tom', 30);
INSERT INTO user (id, username, password, name, age)  VALUES (2, 'cindy', '123456', 'cindy', 29);

INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);