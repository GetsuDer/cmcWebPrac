CREATE TABLE IF NOT EXISTS StaffMembers (
	member_id serial PRIMARY KEY,
    member_name text,
    address text,
    work_start date,
    education text
);

CREATE TABLE IF NOT EXISTS Departments (
	department_id serial PRIMARY KEY,
    department_name text,
    head_department int references Departments(department_id),
    director int references StaffMembers(member_id)
);

CREATE TABLE IF NOT EXISTS Positions (
	position_id serial PRIMARY KEY,
    position_name text,
    responsibilities text,
    department int references Departments(department_id),
    size int
);
    
CREATE TABLE IF NOT EXISTS Employees (
    employee_id serial PRIMARY KEY,
	position int references Positions(position_id),
    staff_member int references StaffMembers(member_id),
    start_time date,
    end_time date
);   
