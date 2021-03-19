package test;

import DAO.*;
import logic.*;
import DAO.Factory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PositionTest {
    
    @Test
    public void addPosition() throws SQLException {

        String name = "Test position name";
        String responsibilities = "Test responsibilities";
        Long size = Long.valueOf(2);

        Department department = new Department(); 
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();

        departmentDAO.addDepartment(department);
        Position position = new Position(name, responsibilities, department, size);
        positionDAO.addPosition(position);

        Position loadedPosition = positionDAO.getPositionById(position.getId());
        Assert.assertTrue(position.my_equals(loadedPosition));
    }

    @Test
    public void updatePosition() throws SQLException {
        String name = "Test position name";
        String responsibilities = "Test responsibilities";
        Long size = Long.valueOf(2);
        Department department = new Department();
        Department department2 = new Department(); 
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();

        departmentDAO.addDepartment(department);
        departmentDAO.addDepartment(department2);
        Position position = new Position(name, responsibilities, department, size);
        Position newPosition = new Position(name + "2", responsibilities + "2", department2, size + 2);
        positionDAO.addPosition(position);
        newPosition.setId(position.getId());
        positionDAO.updatePosition(newPosition);
        Position loadedPosition = positionDAO.getPositionById(position.getId());
        Assert.assertTrue(loadedPosition.my_equals(newPosition));
    }

    @Test
    public void deletePosition() throws SQLException {
        Position position = new Position();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        positionDAO.addPosition(position);
        positionDAO.deletePosition(position);
        Collection<Position> positions = positionDAO.getAllPositions();
        for (Position pos : positions) {
            Assert.assertFalse(pos.my_equals(position));
        }
    }

    @Test
    public void getPositionsByDepartment() throws SQLException {
        Department department = new Department();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        ArrayList<Position> positions = new ArrayList<Position>();

        departmentDAO.addDepartment(department);
        for (int i = 0; i < 3; i++) {
            Position position = new Position(null, null, department, Long.valueOf(1));
            positionDAO.addPosition(position);
            positions.add(position);
        }   
        Collection<Position> loadedPositions = positionDAO.getPositionsByDepartment(department);
        Assert.assertTrue(loadedPositions.size() == positions.size());
        for (Position pos : positions) {
            boolean founded = false;
            for (Position loadedPosition : loadedPositions) {
                if (loadedPosition.my_equals(pos)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }

    @Test
    public void getAllPositionsByStaffMember() throws SQLException {
        Department department1 = new Department();
        Department department2 = new Department();
        StaffMember member = new StaffMember();
        
        List<Position> positions = new ArrayList<Position>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();

        departmentDAO.addDepartment(department1);
        departmentDAO.addDepartment(department2);
        staffMemberDAO.addStaffMember(member);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2009, 3, 3, 0, 0, 0);
        Date endTime = calendar.getTime();

        for (int i = 0; i < 3; i++) {
            Position position1 = new Position(null, null, department1, Long.valueOf(1));
            Position position2 = new Position(null, null, department2, Long.valueOf(1));
            positionDAO.addPosition(position1);
            positionDAO.addPosition(position2);
            positions.add(position1);
            positions.add(position2);
            Employee employee1 = new Employee(position1, member, null, null);
            Employee employee2 = new Employee(position2, member, null, endTime);
            employeeDAO.addEmployee(employee1);
            employeeDAO.addEmployee(employee2);
        }

        Collection<Position> loadedPositions = positionDAO.getAllPositionsByStaffMember(member);
        Assert.assertTrue(loadedPositions.size() == positions.size());
        for (Position position : positions) {
            boolean founded = false;
            for (Position loadedPosition : loadedPositions) {
                if (loadedPosition.my_equals(position)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }   
    @Test
    public void getCurrentPositionsByStaffMember() throws SQLException {
        Department department1 = new Department();
        Department department2 = new Department();
        StaffMember member = new StaffMember();
        
        List<Position> positions = new ArrayList<Position>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();

        departmentDAO.addDepartment(department1);
        departmentDAO.addDepartment(department2);
        staffMemberDAO.addStaffMember(member);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2004, 1, 4, 0, 0, 0);
        Date endTime = calendar.getTime();

        for (int i = 0; i < 3; i++) {
            Position position1 = new Position(null, null, department1, Long.valueOf(1));
            Position position2 = new Position(null, null, department2, Long.valueOf(1));
            positionDAO.addPosition(position1);
            positionDAO.addPosition(position2);
            positions.add(position2); //add only current position
            Employee employee1 = new Employee(position1, member, null, endTime);
            Employee employee2 = new Employee(position2, member, null, null);
            employeeDAO.addEmployee(employee1);
            employeeDAO.addEmployee(employee2);
        }

        Collection<Position> loadedPositions = positionDAO.getCurrentPositionsByStaffMember(member);
        Assert.assertTrue(loadedPositions.size() == positions.size());
        for (Position position : positions) {
            boolean founded = false;
            for (Position loadedPosition : loadedPositions) {
                if (loadedPosition.my_equals(position)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }
    
    @Test
    public void getPastPositionsByStaffMember() throws SQLException {
        Department department1 = new Department();
        Department department2 = new Department();
        StaffMember member = new StaffMember();
        
        List<Position> positions = new ArrayList<Position>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();

        departmentDAO.addDepartment(department1);
        departmentDAO.addDepartment(department2);
        staffMemberDAO.addStaffMember(member);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2008, 1, 1, 0, 0, 0);
        Date endTime = calendar.getTime();

        for (int i = 0; i < 3; i++) {
            Position position1 = new Position(null, null, department1, Long.valueOf(1));
            Position position2 = new Position(null, null, department2, Long.valueOf(1));
            positionDAO.addPosition(position1);
            positionDAO.addPosition(position2);
            positions.add(position2); // adding only past position
            Employee employee1 = new Employee(position1, member, null, null);
            Employee employee2 = new Employee(position2, member, null, endTime);
            employeeDAO.addEmployee(employee2);
            employeeDAO.addEmployee(employee1);
        }

        Collection<Position> loadedPositions = positionDAO.getPastPositionsByStaffMember(member);
        Assert.assertTrue(loadedPositions.size() == positions.size());
        for (Position position : positions) {
            boolean founded = false;
            for (Position loadedPosition : loadedPositions) {
                if (loadedPosition.my_equals(position)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }


}
