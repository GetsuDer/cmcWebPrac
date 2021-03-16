package test;

import DAO.Impl.DepartmentDAOImpl;
import DAO.DepartmentDAO;
import logic.Department;
import DAO.Factory;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.sql.SQLException;

public class DepartmentTest {

    @Test
    public void createDepartmentTest() {
        Long id = Long.valueOf(1231);
        String name = "TestDepartment";
        Long head_department = Long.valueOf(0);
        Long director = Long.valueOf(0);
        Department department = new Department(id, name, head_department, director);
        Assert.assertEquals(department.getId(), id);
        Assert.assertEquals(department.getName(), name);
        Assert.assertEquals(department.getHeadDepartment(), head_department);
        Assert.assertEquals(department.getDirector(), director);
    }

    @Test
    public void changeDepartmentTest() {
        Long id = Long.valueOf(1231);
        Long new_id = Long.valueOf(1232);

        String name = "TestDepartment";
        String new_name = "TestDepartmentNew";

        Long head_department = Long.valueOf(0);
        Long new_head_department = Long.valueOf(1);

        Long director = Long.valueOf(0);
        Long new_director = Long.valueOf(1);

        Department department = new Department(id, name, head_department, director);
        department.setId(new_id);
        department.setName(new_name);
        department.setHeadDepartment(new_head_department);
        department.setDirector(new_director);

        Assert.assertEquals(department.getId(), new_id);
        Assert.assertEquals(department.getName(), new_name);
        Assert.assertEquals(department.getHeadDepartment(), new_head_department);
        Assert.assertEquals(department.getDirector(), new_director);
    }

    @Test
    public void loadDepartmentTest() throws SQLException {
        Long id = Long.valueOf(1231);
        String name = "TestDepartment";
        Long head_department = id;
        Long director = Long.valueOf(0);
        Department department = new Department(id, name, head_department, director);
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        departmentDAO.addDepartment(department);
        Department loaded_department = departmentDAO.getDepartmentById(id);
        Assert.assertEquals(department.getId(), loaded_department.getId());
        Assert.assertEquals(department.getName(), loaded_department.getName());
        Assert.assertEquals(department.getHeadDepartment(), loaded_department.getHeadDepartment());
        Assert.assertEquals(department.getDirector(), loaded_department.getDirector());
    } 

}
