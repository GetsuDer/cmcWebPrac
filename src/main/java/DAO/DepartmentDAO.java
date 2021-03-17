package DAO;

import logic.Department;
import logic.StaffMember;

import java.util.Collection;
import java.sql.SQLException;

/**
 *  Interface for class Department
 */
public interface DepartmentDAO {
    /** Adding new department into table
     *  @param department department to be added
     */
    public void addDepartment(Department department) throws SQLException;
    
    /** Updating department
     *  @param department New department
     */
    public void updateDepartment(Department department) throws SQLException;
    
    /** Getter for department
     * @param department_id Department to be get id
     */
    public Department getDepartmentById(Long department_id) throws SQLException;
    
    /** Get all existing departments
     * @return Returns collection of all departments
     */
    public Collection getAllDepartments() throws SQLException;
    
    /** Delete department
     * @param department Department to be deleted
     */
    public void deleteDepartment(Department department) throws SQLException;
    
    /** Get departments ruled by specified director
     * @param director Departments director
     * @return Returns departments ruled by director
     */
    public Collection getDepartmentsByDirector(StaffMember director) throws SQLException;
}
