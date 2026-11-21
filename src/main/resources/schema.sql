CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT,
                                     email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN,
    PRIMARY KEY (id)
    )