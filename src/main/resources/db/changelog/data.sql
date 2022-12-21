INSERT INTO product(id, name, price, promotional_item)
VALUES (1,'apple','1',true),
       (2,'milk','2',false),
       (3,'eggs','3',false),
       (4,'cheese','10',false);


SELECT SETVAL('product_id_seq', (SELECT MAX(id) FROM product));

INSERT INTO discount_card(id, name, bit)
VALUES (1,'12345',10),
       (2,'123456789',15),
       (3,'11',5),
       (4,'0987654321',3);


SELECT SETVAL('discount_card_id_seq', (SELECT MAX(id) FROM discount_card));