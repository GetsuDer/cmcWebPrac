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
    head_department int references Departments(department_id) ON DELETE SET NULL ON UPDATE CASCADE,
    director int references StaffMembers(member_id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Positions (
	position_id serial PRIMARY KEY,
    position_name text,
    responsibilities text,
    department int references Departments(department_id) ON DELETE SET NULL ON UPDATE CASCADE,
    size int
);
    
CREATE TABLE IF NOT EXISTS Employees (
    employee_id serial PRIMARY KEY,
	position int references Positions(position_id) ON DELETE CASCADE ON UPDATE CASCADE,
    staff_member int references StaffMembers(member_id) ON DELETE CASCADE ON UPDATE CASCADE,
    start_time date,
    end_time date
);   
