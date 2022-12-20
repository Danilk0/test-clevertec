CREATE TABLE IF NOT EXISTS product
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE,
    price DECIMAL NOT NULL,
    promotional_item BOOLEAN
);

CREATE TABLE IF NOT EXISTS discount_card
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE,
    bit INT
);

drop table product;

