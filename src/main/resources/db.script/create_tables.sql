DROP TABLE IF EXISTS technicians;
DROP TABLE IF EXISTS managers;
DROP TABLE IF EXISTS adresses;
DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS vehicules;

CREATE TABLE adresses
(
    id     SERIAL PRIMARY KEY NOT NULL,
    streetNumber INT,
    road   VARCHAR(255),
    city   VARCHAR(255)
);

CREATE TABLE managers
(
    id           SERIAL PRIMARY KEY NOT NULL,
    firstname    VARCHAR(255),
    lastname     VARCHAR(255),
    phonenumber  VARCHAR(10),
    mobilenumber VARCHAR(10)
);

CREATE TABLE technicians
(
    id        SERIAL PRIMARY KEY NOT NULL,
    firstname VARCHAR(255),
    lastname  VARCHAR(255),
    age       VARCHAR(100),
    manager_id INT,
    adresse_id INT,
    FOREIGN KEY (manager_id) REFERENCES managers(id),
    FOREIGN KEY (adresse_id) REFERENCES adresses(id)
);

CREATE TABLE vehicules (
    id        SERIAL PRIMARY KEY NOT NULL,
    plate VARCHAR(7),
    brand VARCHAR(200),
    yearConstruction VARCHAR(4),
    techinician_id INT,
    FOREIGN KEY (techinician_id) REFERENCES technicians(id)
);

CREATE TABLE works (
    id SERIAL PRIMARY KEY NOT NULL,
    workName VARCHAR(255),
    workPrice VARCHAR(255)
);

INSERT INTO adresses (streetNumber, road, city)
VALUES ('1', 'Place de l\'eglise', 'Paris');
INSERT INTO adresses (streetNumber, road, city)
VALUES ('13', 'Place de la Mairie', 'Rennes');
INSERT INTO adresses (streetNumber, road, city)
VALUES ('56', 'Chemin des Brumes', 'Brest');
INSERT INTO adresses (streetNumber, road, city)
VALUES ('65', 'Rue des Platanes', 'Lannion');
INSERT INTO adresses (streetNumber, road, city)
VALUES ('23', 'rue de la gare', 'Rennes');

INSERT INTO managers (firstname, lastname, phonenumber, mobilenumber)
VALUES ('KENOBI', 'Obi-Wan', '0243456789', '0612345678');
INSERT INTO managers (firstname, lastname, phonenumber, mobilenumber)
VALUES ('Skywalker', 'Luke', '0233451189', '0612345644');
INSERT INTO managers (firstname, lastname, phonenumber, mobilenumber)
VALUES ('Vador', 'Dark', '0233456700', '0610005678');


INSERT INTO technicians (firstname, lastname, age, manager_id, adresse_id)
VALUES ('Trooper', '01', '24', 0, 0);
INSERT INTO technicians (firstname, lastname, age, manager_id, adresse_id)
VALUES ('Troper', '02', '34', 1, 1);
INSERT INTO technicians (firstname, lastname, age, manager_id, adresse_id)
VALUES ('Troopper', '03', '55', 2, 2);
INSERT INTO technicians (firstname, lastname, age, manager_id, adresse_id)
VALUES ('Trouper', '04', '66', 2, 0);
INSERT INTO technicians (firstname, lastname, age, manager_id, adresse_id)
VALUES ('Trop peur', '05', '23', 1, 1);
INSERT INTO technicians (firstname, lastname, age, manager_id, adresse_id)
VALUES ('Tropure', '06', '22', 0, 2);

INSERT INTO vehicules (plate, brand, yearConstruction, techinician_id)
VALUES ('66TYU56', 'Opel', '1923', 0);
INSERT INTO vehicules (plate, brand, yearConstruction, techinician_id)
VALUES ('66TYU22', 'Porche', '2345', 1);
INSERT INTO vehicules (plate, brand, yearConstruction, techinician_id)
VALUES ('66TYU11', 'BMW', '1234', 2);
INSERT INTO vehicules (plate, brand, yearConstruction, techinician_id)
VALUES ('44TYU56', 'RENAULT', '4231', 3);
INSERT INTO vehicules (plate, brand, yearConstruction, techinician_id)
VALUES ('55TUU56', 'DACIA', '1111', 4);
INSERT INTO vehicules (plate, brand, yearConstruction, techinician_id)
VALUES ('66TEE99', 'FERRARI', '2222', 5);

INSERT INTO works (workName, workPrice)
VALUES ('Eglise', '234567');
INSERT INTO works (workName, workPrice)
VALUES ('Station', '345345');
INSERT INTO works (workName, workPrice)
VALUES ('Ecole', '900000');
INSERT INTO works (workName, workPrice)
VALUES ('Route', '1200000');



