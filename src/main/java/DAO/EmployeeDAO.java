package DAO;

import logic.Employee;

import java.util.Collection;
import java.sql.SQLException;

public interface EmployeeDAO {
    public void addEmployee(Employee employee) throws SQLException;
    public void updateEmployee(Long employee_id, Employee employee) throws SQLException;
    public Employee getEmployeeById(Long employee_id) throws SQLException;
    public Collection getAllEmployees() throws SQLException;
    public void deleteEmployee(Employee employee) throws SQLException;
}
