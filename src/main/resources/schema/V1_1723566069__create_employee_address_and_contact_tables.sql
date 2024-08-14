
CREATE TABLE IF NOT EXISTS employees(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    birthdate DATE NOT NULL,
    gender ENUM('Male', 'Female', 'Non-binary', 'Other'),
    marital_status ENUM('Single', 'Married', 'Divorced', 'Widowed', 'Separated', 'Domestic Partnership'),
    emp_position VARCHAR(50) NOT NULL,
    date_hired DATE NOT NULL
--    address_id INT NOT NULL,
--    contact_id INT NOT NULL,
--    FOREIGN KEY (address_id) REFERENCES address(id),
--    FOREIGN KEY (contact_id) REFERENCES contact(id)
);

CREATE TABLE IF NOT EXISTS address(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    is_primary BOOLEAN,
    address_details TEXT NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS contact(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    is_primary BOOLEAN DEFAULT FALSE,
    contact_details TEXT NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
