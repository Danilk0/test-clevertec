--liquibase formatted sql

--changeset dmoskalyuk:1
CREATE TABLE IF NOT EXISTS product
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE,
    price DECIMAL NOT NULL,
    promotional_item BOOLEAN
);

--changeset dmoskalyuk:2
CREATE TABLE IF NOT EXISTS discount_card
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE,
    bit INT
);

