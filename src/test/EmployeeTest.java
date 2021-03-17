package test;

import DAO.EmployeeDAO;
import logic.Employee;
import DAO.Factory;

import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;

import org.testng.annotations.Test;
import org.testng.Assert;

public class EmployeeTest {
    
    @Test
    public void loadEmployee() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 11, 20, 0, 0, 0);
        Date startTime = calendar.getTime();
        
        Employee employee = new Employee(null, null, startTime, null);

        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        try {
            employeeDAO.addEmployee(employee);
            Employee loadedEmployee = employeeDAO.getEmployeeById(employee.getId());
            Assert.assertTrue(employee.my_equals(loadedEmployee));
        } catch (SQLException e) {
            System.out.println(e);
            Assert.assertTrue(false);
        }
    }

}
