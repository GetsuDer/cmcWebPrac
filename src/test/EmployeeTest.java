package test;

import DAO.*;
import logic.*;
import DAO.Factory;

import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collection;

import org.testng.annotations.Test;
import org.testng.Assert;

public class EmployeeTest {
    
    @Test
    public void addEmployee() throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 11, 20, 0, 0, 0);
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();        

        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        Position position = new Position();
        StaffMember member = new StaffMember();
        positionDAO.addPosition(position);
        memberDAO.addStaffMember(member);

        Employee employee = new Employee(position, member, startTime, endTime);
        employeeDAO.addEmployee(employee);
        Employee loadedEmployee = employeeDAO.getEmployeeById(employee.getId());
        Assert.assertTrue(employee.my_equals(loadedEmployee));
    }
    
    @Test
    public void updateEmployee() throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 11, 20, 0, 0, 0);
        Date startTime = calendar.getTime();
        Date endTime = calendar.getTime();

        calendar.set(2021, 10, 20, 0, 0, 0);
        Date startTime2 = calendar.getTime();
        Date endTime2 = calendar.getTime();        

        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();

        StaffMember member = new StaffMember();
        StaffMember member2 = new StaffMember();

        Position position = new Position();
        Position position2 = new Position();

        memberDAO.addStaffMember(member);
        positionDAO.addPosition(position);
        Employee employee = new Employee(position, member, startTime, endTime);
        Employee newEmployee = new Employee(position2, member2, startTime2, endTime2);
        employeeDAO.addEmployee(employee);
        newEmployee.setId(employee.getId());
        Employee loadedEmployee = employeeDAO.getEmployeeById(employee.getId());
        Assert.assertTrue(employee.my_equals(loadedEmployee));
    }

    @Test
    public void deleteEmployee() throws SQLException {
        Employee employee = new Employee();

        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        employeeDAO.addEmployee(employee);
        employeeDAO.deleteEmployee(employee);
        Collection<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee emp : employees) {
            Assert.assertFalse(emp.my_equals(employee));
        }
    }

    @Test
    public void getEmployeesByPosition() throws SQLException {
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Position position = new Position();
        positionDAO.addPosition(position);
        for (int i = 0; i < 7; i++) {
            Employee employee = new Employee(position, null, null, null);
            employeeDAO.addEmployee(employee);
            employees.add(employee);
        }

        Collection<Employee> loadedEmployees = employeeDAO.getEmployeesByPosition(position);
        Assert.assertTrue(loadedEmployees.size() == employees.size());
        for (Employee emp : employees) {
            boolean founded = false;
            for (Employee loadedEmp : loadedEmployees) {
                if (loadedEmp.my_equals(emp)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }

    @Test
    public void getEmployeesByStaffMember() throws SQLException {
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        ArrayList<Employee> employees = new ArrayList<Employee>();

        StaffMember member = new StaffMember();
        memberDAO.addStaffMember(member);
        for (int i = 0; i < 7; i++) {
            Employee employee = new Employee(null, member, null, null);
            employeeDAO.addEmployee(employee);
            employees.add(employee);
        }
        Collection<Employee> loadedEmployees = employeeDAO.getEmployeesByStaffMember(member);
        Assert.assertTrue(loadedEmployees.size() == employees.size());
        for (Employee emp : employees) {
            boolean founded = false;
            for (Employee loadedEmp : loadedEmployees) {
                if (loadedEmp.my_equals(emp)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }

}
