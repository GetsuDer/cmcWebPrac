INSERT INTO StaffMembers (member_id, member_name, address, work_start, education)
VALUES
 (1, 'Ivanov Ivan Ivanovich', 'Address1', '2014-11-04', 'MSU'),
 (2, 'Petrov Petr Petrovich', 'Address2', '2015-1-06', 'MSU'),
 (3, 'Sidorov Sidor Sidorovich', 'Address3', '2016-9-08', 'MSU'),
 (4, 'Ivanov Petr Ivanovich', 'Address4', '2020-3-14', 'MSU'),
 (5, 'Ivanov Sidor Ivanovich', 'Address5', '2012-4-24', 'MSU'),
 (6, 'Petrov Ivan Ivanovich', 'Address6', '2009-8-05', 'MSU'),
 (7, 'Sidorov Ivan Ivanovich', 'Address7', '2008-6-09', 'MSU'),
 (8, 'Ivanov Ivan Sidorovich', 'Address8', '2009-2-09', 'MSU'),
 (9, 'Ivanov Ivan Petrovich', 'Address9', '2003-6-15', 'MSU'),
 (10, 'Ivanov Alex Ivanovich', 'Address10', '2012-5-16', 'MSU'),
 (11, 'Ivanova Maria Ivanovna', 'Address11', '2014-4-17', 'MSU');
 
 INSERT INTO Departments (department_id, department_name, head_department, director)
 VALUES
	(1, 'Department1', 1, 1),
	(2, 'Department2', 1, 2),
	(3, 'Department3', 1, 3),
	(4, 'Department4', 2, 4),
	(5, 'Department5', 2, 5);
    
INSERT INTO Positions (position_id, position_name, responsibilities, department, size)
VALUES
	(1, 'Position1', 'Something', 1, 2),
	(2, 'Position2', 'Something', 1, 1),
	(3, 'Position3', 'Something', 1, 1),
	(4, 'Position4', 'Something', 2, 1),
	(5, 'Position5', 'Something', 3, 1),
	(6, 'Position6', 'Something', 4, 1),
	(7, 'Position7', 'Something', 5, 1);

INSERT INTO Employees (position, staff_member, start_time, end_time)
VALUES
	(1, 1, '2014-11-04', NULL),
	(1, 2, '2015-1-06', NULL),
	(2, 3, '2016-9-11', NULL),
	(4, 4, '2020-3-14', NULL),
	(5, 5, '2012-4-29', NULL);
