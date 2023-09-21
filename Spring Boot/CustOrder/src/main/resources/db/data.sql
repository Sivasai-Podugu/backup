INSERT INTO customer (first_name, last_name, address) VALUES ('Siva', 'Podugu', 'Srikakulam');
INSERT INTO customer (first_name, last_name, address) VALUES ('Arjun', 'P', 'Bangalore');
INSERT INTO customer (first_name, last_name, address) VALUES ('Jaan', 'P', 'East G');



INSERT INTO orders (customer_id, order_date, order_status, payment_done) VALUES (1, '2023-08-03 10:00:00', 'PROCESSING', true);
INSERT INTO orders (customer_id, order_date, order_status, payment_done) VALUES (2, '2023-08-03 11:30:00', 'SHIPPED', true);
INSERT INTO orders (customer_id, order_date, order_status, payment_done) VALUES (1, '2023-08-03 13:45:00', 'DELIVERED', true);
INSERT INTO orders (customer_id, order_date, order_status, payment_done) VALUES (3, '2023-08-03 09:15:00', 'PROCESSING', false);
