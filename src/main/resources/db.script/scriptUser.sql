CREATE TABLE users (id SERIAL PRIMARY KEY NOT NULL,
                    username VARCHAR(30),
                    password VARCHAR(255),
                    mail VARCHAR(255),
                    grants VARCHAR(255));

INSERT INTO users (username, password, mail, grants) VALUES ('admin', '$2y$10$vko34oH32uca.EW2mBgaUO/q53s/cePx0Z/MNbTze7Xdnwkn/1zRi', 'admin@test.fr', 'ADMIN');
INSERT INTO users (username, password, mail, grants) VALUES ('user', '$2a$04$hAMxwSrpBJ8J3uH8KITYuO.rz90MsGyY2bEiCSvXnnIIku1SL5Z2e', 'user@test.fr', 'USER');