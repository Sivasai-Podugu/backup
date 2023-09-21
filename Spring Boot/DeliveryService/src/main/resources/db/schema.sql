-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop the student table if it exists
DROP TABLE IF EXISTS orders, driver, order_item, delivery_status;


-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE delivery_status(
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(50) NOT NULL,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE driver(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         phone VARCHAR(20) NOT NULL,
                         license_number VARCHAR(50) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE orders(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        customer_name VARCHAR(100) NOT NULL,
                        customer_email VARCHAR(100) NOT NULL,
                        delivery_address VARCHAR(200) NOT NULL,
                        driver_id INT,
                        status_id INT NOT NULL,
                        date DATE NOT NULL,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (driver_id) REFERENCES driver(id),
                        FOREIGN KEY (status_id) REFERENCES delivery_status(id)
);

CREATE TABLE order_item (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             orders_id INT NOT NULL,
                             name VARCHAR(100) NOT NULL,
                             quantity INT NOT NULL,
                             price DECIMAL(10, 2) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             FOREIGN KEY (orders_id) REFERENCES orders (id)
);





