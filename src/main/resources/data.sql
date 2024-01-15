INSERT IGNORE INTO users (id, age, email, lastname, name, password)
VALUES (1, 30, 'admin@mail.ru', 'admin', 'admin', '$2a$12$im/KwQaC6Ia.Dj9BiFYmxeSh1ClnDt8YAGnHYqieTa.8Jn7dhp4Kq');

INSERT IGNORE INTO users (id, age, email, lastname, name, password)
VALUES (2, 30, 'user@mail.ru', 'user', 'user', '$2a$12$QKGsxf11dkQ9jthWA9S64ufdCHRGfeL0gINxSEYmAnujjoJH.DAPG');

INSERT IGNORE INTO roles (id, name) VALUES (1,'ADMIN');

INSERT IGNORE INTO roles (id, name) VALUES (2,'USER');

INSERT IGNORE INTO users_roles (user_id, roles_id) VALUES (1,1);

INSERT IGNORE INTO users_roles (user_id, roles_id) VALUES (2,2);

