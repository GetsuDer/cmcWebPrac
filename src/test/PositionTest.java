package test;

import DAO.*;
import logic.*;
import DAO.Factory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PositionTest {
    
    @Test
    public void addPosition() {

        String name = "Test position name";
        String responsibilities = "Test responsibilities";
        Long size = Long.valueOf(2);

        Department department = new Department(); 
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();

        try {
            departmentDAO.addDepartment(department);
            Position position = new Position(name, responsibilities, department, size);
            positionDAO.addPosition(position);

            Position loadedPosition = positionDAO.getPositionById(position.getId());
            Assert.assertTrue(position.my_equals(loadedPosition));
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void updatePosition() {
        String name = "Test position name";
        String responsibilities = "Test responsibilities";
        Long size = Long.valueOf(2);
        Department department = new Department();
        Department department2 = new Department(); 
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();

        try {
            departmentDAO.addDepartment(department);
            departmentDAO.addDepartment(department2);
            Position position = new Position(name, responsibilities, department, size);
            Position newPosition = new Position(name + "2", responsibilities + "2", department2, size + 2);

            positionDAO.addPosition(position);
            newPosition.setId(position.getId());
            positionDAO.updatePosition(newPosition);

            Position loadedPosition = positionDAO.getPositionById(position.getId());
            Assert.assertTrue(loadedPosition.my_equals(newPosition));
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }  
    }

    @Test
    public void deletePosition() {
        Position position = new Position();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        try {
            positionDAO.addPosition(position);
            positionDAO.deletePosition(position);
            Collection positionsLoaded = positionDAO.getAllPositions();
            ArrayList<Position> positions = new ArrayList<Position>(positionsLoaded);
            for (Position pos : positions) {
                Assert.assertFalse(pos.my_equals(position));
            }
        } catch(SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPositionsByDepartment() {
        Department department = new Department();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        ArrayList<Position> positions = new ArrayList<Position>();

        try {
            departmentDAO.addDepartment(department);
            for (int i = 0; i < 3; i++) {
                Position position = new Position(null, null, department, Long.valueOf(1));
                positionDAO.addPosition(position);
            }   
            Collection loadedPositions = positionDAO.getPositionsByDepartment(department);
            for (Position pos : positions) {
                boolean founded = false;
                for (Object obj : loadedPositions) {
                    Position loadedPosition = (Position) obj;
                    if (loadedPosition.my_equals(pos)) {
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

    @Test
    public void getPositionsByStaffMember() {
        Department department1 = new Department();
        Department department2 = new Department();
        StaffMember member = new StaffMember();
        
        List<Position> positions = new ArrayList<Position>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();

        try {
            departmentDAO.addDepartment(department1);
            departmentDAO.addDepartment(department2);
            staffMemberDAO.addStaffMember(member);

            for (int i = 0; i < 3; i++) {
                Position position1 = new Position(null, null, department1, Long.valueOf(1));
                Position position2 = new Position(null, null, department2, Long.valueOf(1));
                positionDAO.addPosition(position1);
                positionDAO.addPosition(position2);
                positions.add(position1);
                positions.add(position2);
                Employee employee1 = new Employee(position1, member, null, null);
                Employee employee2 = new Employee(position2, member, null, null);
                employeeDAO.addEmployee(employee1);
                employeeDAO.addEmployee(employee2);
            }

            Collection loadedPositions = positionDAO.getPositionsByStaffMember(member);
            for (Position position : positions) {
                boolean founded = false;
                for (Object obj : loadedPositions) {
                    Position loadedPosition = (Position) obj;
                    if (loadedPosition.my_equals(position)) {
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
