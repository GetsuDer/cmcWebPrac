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
    public void addDepartment() {
        String name = "TestDepartment";
        Department headDepartment = new Department();
        StaffMember director = new StaffMember();
        
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        try {
            departmentDAO.addDepartment(headDepartment);
            staffMemberDAO.addStaffMember(director);
            Department department = new Department(name, headDepartment, director);
            departmentDAO.addDepartment(department);
            Department loaded_department = departmentDAO.getDepartmentById(department.getId());
            Assert.assertTrue(department.my_equals(loaded_department));
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
    } 

    @Test
    public void updateDepartment() {
        String name = "TestDepartment";
        Department headDepartment = new Department();
        Department headDepartment2 = new Department();

        StaffMember director = new StaffMember();
        StaffMember director2 = new StaffMember();

        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        try {
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
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
    }

    @Test 
    public void deleteDepartment() {
        Department department = new Department();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();

        try {
            departmentDAO.addDepartment(department);
            departmentDAO.deleteDepartment(department);
            Collection departmentsCollection = departmentDAO.getAllDepartments();
            ArrayList<Department> departments = new ArrayList<Department>(departmentsCollection);
            for (Department dep : departments) {
                Assert.assertFalse(dep.my_equals(department));
            }
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }

    }

    @Test
    public void getDepartmentsByDirector() {
        StaffMember director = new StaffMember();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();

        ArrayList<Department> departments = new ArrayList<Department>();

        try {
            staffMemberDAO.addStaffMember(director);
            for (int i = 0; i < 3; i++) {
                Department department = new Department();
                department.setDirector(director);
                departmentDAO.addDepartment(department);
            }

            Collection loadedDepartments = departmentDAO.getDepartmentsByDirector(director);
            for (Department dep : departments) {
                boolean founded = false;
                for (Object obj : loadedDepartments) {
                    Department loadedDep = (Department) obj;
                    if (dep.my_equals(loadedDep)) {
                        founded = true;
                    }
                }
                Assert.assertTrue(founded);
            }
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
        
    }
}
