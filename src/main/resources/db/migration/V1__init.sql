CREATE TABLE customers (
    customer_id_owner INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_card VARCHAR(7) UNIQUE NOT NULL,
    name VARCHAR(128) NOT NULL,
    surname VARCHAR(128) NOT NULL,
    address TEXT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE users (
    customer_id_user INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(256),
    password VARCHAR(256)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE mobile_subscribers (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    msisdn VARCHAR(15) UNIQUE NOT NULL,
    customer_id_owner INTEGER NOT NULL,
    customer_id_user INTEGER NOT NULL,
    service_type VARCHAR(15) NOT NULL,
    service_start_date INT NOT NULL DEFAULT (UNIX_TIMESTAMP()),
    FOREIGN KEY (customer_id_owner) REFERENCES customers(customer_id_owner),
    FOREIGN KEY (customer_id_user) REFERENCES users(customer_id_user)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

