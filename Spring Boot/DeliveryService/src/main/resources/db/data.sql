INSERT INTO delivery_status (name) VALUES
                                                ('Pending'),
                                                ('In Transit'),
                                                ('Delivered');


INSERT INTO driver (name, email, phone, license_number)
VALUES
    ('Rama Rao', 'rr@example.com', '1234567890', 'ABC123XYZ'),
    ('Seenu', 'Seenu@example.com', '9876543210', 'DEF456UVW');

INSERT INTO orders (customer_name, customer_email, delivery_address, driver_id, status_id,date, total_amount)
VALUES
    ('Rohit', 'alice@example.com', 'Mumbai', 1, 1, '2023-07-30', 75.50),
    ('Kohli', 'bob@example.com', 'Delhi', 2, 2, '2023-07-31', 45.20);

INSERT INTO order_item (orders_id, name, quantity, price)
VALUES
    (1, 'Product A', 2, 20.00),
    (1, 'Product B', 1, 15.50),
    (2, 'Product C', 3, 8.40);


