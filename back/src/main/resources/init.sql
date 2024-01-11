USE alten_shop;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    inventory_status ENUM('INSTOCK', 'LOWSTOCK', 'OUTOFSTOCK') NOT NULL,
    category ENUM('Accessories', 'Clothing', 'Electronics', 'Fitness') NOT NULL,
    image VARCHAR(255),
    rating INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

Create TABLE users (
    id int NOT NULL AUTO_INCREMENT primary key,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NUll
);