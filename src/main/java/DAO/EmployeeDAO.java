package DAO;

import logic.Employee;

import java.util.Collection;
import java.sql.SQLException;

/**
 * Interface for Employee class
 */
public interface EmployeeDAO {
    /** Add employee into table
     * @param employee Employee to be added
     */
    public void addEmployee(Employee employee) throws SQLException;
    
    /** Update employee into table
     * @param employee New employee to be updated
     */
    public void updateEmployee(Employee employee) throws SQLException;
    
    /** Get employee
     * @param employee_id Employee id
     * @return Returns specified employee
     */
    public Employee getEmployeeById(Long employee_id) throws SQLException;
    
    /** Get all existing employees
     * @return Returns all existing employees
     */
    public Collection getAllEmployees() throws SQLException;
    
    /** Delete current employee
     * @param employee Current employee
     */
    public void deleteEmployee(Employee employee) throws SQLException;
}
