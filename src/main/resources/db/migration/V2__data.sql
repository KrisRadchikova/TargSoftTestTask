-- категории
INSERT INTO employee_category(id, name) VALUES (1, 'HR');
INSERT INTO employee_category(id, name) VALUES (2, 'DevOps');
INSERT INTO employee_category(id, name) VALUES (3, 'Lead');
INSERT INTO employee_category(id, name) VALUES (4, 'Junior');
INSERT INTO employee_category(id, name) VALUES (5, 'Middle');
INSERT INTO employee_category(id, name) VALUES (6, 'Senior');

-- работники
INSERT INTO employee(id, name, employee_category_id) VALUES (1, 'Max', 5);
INSERT INTO employee(id, name, employee_category_id) VALUES (2, 'Kris', 4);
INSERT INTO employee(id, name, employee_category_id) VALUES (3, 'Victor', 6);