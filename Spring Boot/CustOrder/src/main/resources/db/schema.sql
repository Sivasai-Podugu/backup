set foreign_key_checks  =0;
drop table if exists customer;
set foreign_key_checks =1;

CREATE TABLE customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(255),
                          last_name VARCHAR(255),
                          address VARCHAR(255)
);

INSERT INTO customer (first_name, last_name, address) VALUES ('Siva', 'Podugu', 'Srikakulam');
INSERT INTO customer (first_name, last_name, address) VALUES ('Arjun', 'P', 'Bangalore');
INSERT INTO customer (first_name, last_name, address) VALUES ('Jaan', 'P', 'East G');

CREATE TABLE orders (
                        order_id INT AUTO_INCREMENT PRIMARY KEY,
                        customer_id INT,
                        orderDate DATETIME,
                        orderStatus VARCHAR(50),
                        paymentDone BOOLEAN,
                        FOREIGN KEY (customer_id) REFERENCES customer(id)
);

INSERT INTO orders (customer_id, orderDate, orderStatus, paymentDone) VALUES (1, '2023-08-03 10:00:00', 'PROCESSING', true);
INSERT INTO orders (customer_id, orderDate, orderStatus, paymentDone) VALUES (2, '2023-08-03 11:30:00', 'SHIPPED', true);
INSERT INTO orders (customer_id, orderDate, orderStatus, paymentDone) VALUES (1, '2023-08-03 13:45:00', 'DELIVERED', true);
INSERT INTO orders (customer_id, orderDate, orderStatus, paymentDone) VALUES (3, '2023-08-03 09:15:00', 'PROCESSING', false);
