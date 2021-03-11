CREATE DATABASE Staff_Info;
use Staff_Info;
CREATE TABLE Staff_Members (
	member_id serial PRIMARY KEY,
    member_name text,
    address text,
    work_start date,
    education text
);

CREATE TABLE Departments (
	department_id serial PRIMARY KEY,
    department_name text,
    head_department int references Departments(department_id),
    director int references Staff_Members(member_id)
);

CREATE TABLE Positions (
	position_id serial PRIMARY KEY,
    position_name text,
    responsibilities text,
    department int references Departments(department_id),
    size int
);
    
CREATE TABLE Employees (
	position int references Positions(position_id),
    staff_member int references Staff_Members(member_id),
    start_time date,
    end_time date,
    PRIMARY KEY (position, staff_member)
);
    