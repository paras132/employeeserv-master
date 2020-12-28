DROP TABLE IF EXISTS employees;

CREATE TABLE employees
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    date_of_birth VARCHAR(50) NOT NULL,
    line1         VARCHAR(50) NOT NULL,
    line2         VARCHAR(50) DEFAULT NULL,
    city          VARCHAR(50) NOT NULL,
    state         VARCHAR(50) NOT NULL,
    country       VARCHAR(50) NOT NULL,
    zip_code      VARCHAR(50) NOT NULL
);

--INSERT INTO employees(first_name, last_name, date_of_birth, line1, city, state, country, zip_code) VALUES ('test', 'test', 'test', 'test', 'test', 'test', 'test', 'test');