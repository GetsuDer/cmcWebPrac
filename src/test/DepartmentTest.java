package test;

import DAO.Impl.DepartmentDAOImpl;
import DAO.DepartmentDAO;
import logic.*;
import DAO.Factory;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.sql.SQLException;

public class DepartmentTest {
    
    @Test
    public void loadDepartmentTest() {
        String name = "TestDepartment";
        Department department = new Department(name, null, null);
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        try {
            departmentDAO.addDepartment(department);
            Department loaded_department = departmentDAO.getDepartmentById(department.getId());
            Assert.assertTrue(department.my_equals(loaded_department));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    } 
}
