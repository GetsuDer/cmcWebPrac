package DAO;

import logic.Department;
import logic.StaffMember;

import java.util.Collection;
import java.sql.SQLException;

public interface DepartmentDAO {
    public void addDepartment(Department department) throws SQLException;
    public void updateDepartment(Long department_id, Department department) throws SQLException;
    public Department getDepartmentById(Long department_id) throws SQLException;
    public Collection getAllDepartments() throws SQLException;
    public void deleteDepartment(Department department) throws SQLException;
    public Collection getDepartmentsByDirector(StaffMember director) throws SQLException;
}
