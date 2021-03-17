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

public class StaffMemberTest {
    
    @Test
    public void addStaffMember() {
        String name = "Test member name";
        String address = "Test address";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 10, 1, 0, 0, 0);
        Date workStart = calendar.getTime();
        String education = "Test education";
        StaffMember member = new StaffMember(name, address, workStart, education);
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        try {
            memberDAO.addStaffMember(member);
            StaffMember loadedMember = memberDAO.getStaffMemberById(member.getId());
            Assert.assertTrue(member.my_equals(loadedMember));
        
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void updateStaffMember() {
        String name = "Test member name";
        String address = "Test address";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 10, 1, 0, 0, 0);
        Date workStart = calendar.getTime();
        String education = "Test education";
        StaffMember member = new StaffMember(name, address, workStart, education);
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        try {
            memberDAO.addStaffMember(member);
            StaffMember newMember = new StaffMember(name + "2", address + "2", workStart, education + "2");
            newMember.setId(member.getId());
            memberDAO.updateStaffMember(member.getId(), newMember);
            StaffMember loadedMember = memberDAO.getStaffMemberById(member.getId());
            Assert.assertTrue(newMember.my_equals(loadedMember));
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void deleteStaffMember() {
        StaffMember member = new StaffMember();
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        try {
            memberDAO.addStaffMember(member);
            memberDAO.deleteStaffMember(member);
            Collection membersCollection = memberDAO.getAllStaffMembers();
            ArrayList<StaffMember> members = new ArrayList<StaffMember>(membersCollection);
            for (StaffMember listMember : members) {
                Assert.assertFalse(member.my_equals(listMember));
            }
        } catch (SQLException e) {
            System.err.println(e);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getStaffMembersByDepartment() {
        Department department = new Department();
        ArrayList<StaffMember> members = new ArrayList<StaffMember>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        try {
            departmentDAO.addDepartment(department);
            for (int i = 0; i < 3; i++) {
                StaffMember member = new StaffMember();
                staffMemberDAO.addStaffMember(member);
                members.add(member);
                Position position = new Position(null, null, department, Long.valueOf(1));
                positionDAO.addPosition(position);
                Employee employee = new Employee(position, member, null, null);
                employeeDAO.addEmployee(employee);
            }
            Collection loadedMembers = staffMemberDAO.getStaffMembersByDepartment(department);
            for (StaffMember mem : members) {
                boolean founded = false;
                for (Object obj : loadedMembers) {
                    StaffMember loadedMem = (StaffMember) obj;
                    if (loadedMem.my_equals(mem)) {
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
    public void getStaffMembersByPosition() {
        Department department = new Department();
        ArrayList<StaffMember> members = new ArrayList<StaffMember>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
        try {
            departmentDAO.addDepartment(department);
            Position position = new Position(null, null, department, Long.valueOf(3));
            positionDAO.addPosition(position);
            for (int i = 0; i < 3; i++) {
                StaffMember member = new StaffMember();
                staffMemberDAO.addStaffMember(member);
                members.add(member);
                Employee employee = new Employee(position, member, null, null);
                employeeDAO.addEmployee(employee);
            }
            Collection loadedMembers = staffMemberDAO.getStaffMembersByPosition(position);
            for (StaffMember mem : members) {
                boolean founded = false;
                for (Object obj : loadedMembers) {
                    StaffMember loadedMem = (StaffMember) obj;
                    if (loadedMem.my_equals(mem)) {
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
