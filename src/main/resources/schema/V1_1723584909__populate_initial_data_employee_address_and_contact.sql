-- Insert sample employees
INSERT INTO employees (first_name, last_name, middle_name, birthdate, gender, marital_status, emp_position, date_hired)
VALUES
('John', 'Doe', 'A', '1985-04-12', 'Male', 'Married', 'Software Engineer', '2010-06-01'),
('Jane', 'Smith', 'B', '1990-08-23', 'Female', 'Single', 'Product Manager', '2015-09-15');

-- Insert sample addresses
INSERT INTO address (is_primary, address_details, employee_id)
 VALUES
(TRUE, '123 Main St, Springfield, USA', 1),
(FALSE, '456 Elm St, Shelbyville, USA', 1),
(TRUE, '789 Oak St, Capital City, USA', 2);

-- Insert sample contacts
INSERT INTO contact (is_primary, contact_details, employee_id)
VALUES
(TRUE, 'john.doe@example.com', 1),
(FALSE, 'john.doe@workmail.com', 2),
(TRUE, '09055712341', 2);

