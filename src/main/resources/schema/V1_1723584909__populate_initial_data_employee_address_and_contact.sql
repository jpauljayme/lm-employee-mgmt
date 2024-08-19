INSERT INTO employees (first_name, last_name, middle_name, birthdate, gender, marital_status, emp_position, date_hired) VALUES
('John', 'Doe', 'Michael', '1985-05-12', 'Male', 'Married', 'Software Engineer', '2010-08-15'),
('Jane', 'Smith', 'Elizabeth', '1990-02-28', 'Female', 'Single', 'Project Manager', '2015-06-01'),
('Robert', 'Johnson', NULL, '1978-11-30', 'Male', 'Divorced', 'Systems Analyst', '2005-04-10'),
('Emily', 'Davis', 'Anne', '1988-07-19', 'Female', 'Married', 'HR Manager', '2012-03-22'),
('Michael', 'Brown', 'Christopher', '1992-09-05', 'Male', 'Single', 'DevOps Engineer', '2018-11-12'),
('Sarah', 'Wilson', 'Marie', '1982-03-15', 'Female', 'Widowed', 'Finance Director', '2008-01-10'),
('David', 'Taylor', 'James', '1975-01-25', 'Male', 'Married', 'CTO', '2000-05-20'),
('Lisa', 'Martinez', 'Victoria', '1987-08-22', 'Female', 'DomesticPartnership', 'Marketing Specialist', '2013-09-01');

INSERT INTO address (is_primary, address_details, employee_id) VALUES
(TRUE, '123 Main St, Springfield, IL 62704', 1),
(TRUE, '456 Elm St, Boston, MA 02118', 2),
(FALSE, '789 Oak St, Austin, TX 78701', 2),
(TRUE, '321 Maple Ave, Seattle, WA 98109', 3),
(FALSE, '654 Pine St, Denver, CO 80203', 3),
(TRUE, '987 Cedar St, Miami, FL 33101', 4),
(TRUE, '111 Birch St, Los Angeles, CA 90001', 5),
(FALSE, '222 Ash St, San Francisco, CA 94103', 5);

INSERT INTO contact (is_primary, contact_details, employee_id) VALUES
(TRUE, '(217) 555-1234', 1),
(TRUE, '(617) 555-5678', 2),
(FALSE, '(512) 555-8765', 2),
(TRUE, '(206) 555-4321', 3),
(TRUE, '(305) 555-7890', 4),
(FALSE, '(303) 555-9876', 3),
(TRUE, '(323) 555-2345', 5),
(TRUE, '(415) 555-3456', 6);
