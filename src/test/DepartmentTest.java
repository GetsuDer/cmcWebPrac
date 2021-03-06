package test;

import DAO.*;
import logic.*;
import DAO.Factory;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DepartmentTest {
    
    @Test
    public void addDepartment() throws SQLException {
        String name = "TestDepartment";
        Department headDepartment = new Department();
        StaffMember director = new StaffMember();
        
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        
        departmentDAO.addDepartment(headDepartment);   
        staffMemberDAO.addStaffMember(director);
        Department department = new Department(name, headDepartment, director);
        departmentDAO.addDepartment(department);
        Department loaded_department = departmentDAO.getDepartmentById(department.getId());
        Assert.assertTrue(department.my_equals(loaded_department));
    } 

    @Test
    public void updateDepartment() throws SQLException {
        String name = "TestDepartment";
        Department headDepartment = new Department();
        Department headDepartment2 = new Department();

        StaffMember director = new StaffMember();
        StaffMember director2 = new StaffMember();

        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        departmentDAO.addDepartment(headDepartment);
        departmentDAO.addDepartment(headDepartment2);
        staffMemberDAO.addStaffMember(director);
        staffMemberDAO.addStaffMember(director2);
            
        Department department = new Department(name, headDepartment, director);
        Department newDepartment = new Department(name + "2", headDepartment2, director2);
        departmentDAO.addDepartment(department);
        newDepartment.setId(department.getId());
        departmentDAO.updateDepartment(newDepartment);

        Department loaded_department = departmentDAO.getDepartmentById(department.getId());
        Assert.assertTrue(newDepartment.my_equals(loaded_department));
    }

    @Test 
    public void deleteDepartment() throws SQLException {
        Department department = new Department();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();

        departmentDAO.addDepartment(department);
        departmentDAO.deleteDepartment(department);
        Collection<Department> departments = departmentDAO.getAllDepartments();
        for (Department dep : departments) {
            Assert.assertFalse(dep.my_equals(department));
        }

    }

    @Test
    public void getDepartmentsByDirector() throws SQLException {
        StaffMember director = new StaffMember();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();

        ArrayList<Department> departments = new ArrayList<Department>();

        staffMemberDAO.addStaffMember(director);
        for (int i = 0; i < 3; i++) {
            Department department = new Department();
            department.setDirector(director);
            departmentDAO.addDepartment(department);
            departments.add(department);
        }

        Collection<Department> loadedDepartments = departmentDAO.getDepartmentsByDirector(director);
        Assert.assertTrue(loadedDepartments.size() == departments.size());
        for (Department dep : departments) {
            boolean founded = false;
            for (Department loadedDep : loadedDepartments) {
                if (dep.my_equals(loadedDep)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }

    @Test
    public void getDepartmentsByDirectorNone() throws SQLException {
        StaffMember director = new StaffMember();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        staffMemberDAO.addStaffMember(director);
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        Collection<Department> deps = departmentDAO.getDepartmentsByDirector(director);
        Assert.assertTrue(deps.size() == 0);
    }

    @Test
    public void getSubDepartments() throws SQLException {
        Department headDepartment = new Department();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        ArrayList<Department> departments = new ArrayList<Department>();
        departmentDAO.addDepartment(headDepartment);
        for (int i = 0; i < 5; i++) {
            Department dep = new Department();
            dep.setHeadDepartment(headDepartment);
            departmentDAO.addDepartment(dep);
            departments.add(dep);
        }
        Collection<Department> loadedDepartments = departmentDAO.getSubDepartments(headDepartment);
        Assert.assertEquals(departments.size(), loadedDepartments.size());
        for (Department dep : departments) {
            boolean founded = false;
            for (Department loadedDep : loadedDepartments) {
                if (loadedDep.my_equals(dep)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }

    @Test
    public void getSubDepartmentsNone() throws SQLException {
        Department department = new Department();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        departmentDAO.addDepartment(department);
        Collection<Department> subDepartments = departmentDAO.getSubDepartments(department);
        Assert.assertTrue(subDepartments.size() == 0);
    }

    @Test
    public void getDepartmentsByName() throws SQLException {
        Department depA = new Department();
        depA.setName("A_dep");
        Department depB = new Department();
        depB.setName("dep_B");
        Department depC = new Department();
        depC.setName("StangeName");

        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        departmentDAO.addDepartment(depA);
        departmentDAO.addDepartment(depB);
        departmentDAO.addDepartment(depC);

        Collection<Department> departments = departmentDAO.getDepartmentsByName("dep");
        Assert.assertTrue(departments.size() >= 2); // there can be more names with dep already
        for (Department dep : departments) {
            Assert.assertTrue(dep.getName().toLowerCase().contains("dep"));
        }
    }
}
