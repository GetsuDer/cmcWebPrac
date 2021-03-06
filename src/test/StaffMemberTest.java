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
    public void addStaffMember() throws SQLException {
        String name = "Test member name";
        String address = "Test address";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 10, 1, 0, 0, 0);
        Date workStart = calendar.getTime();
        String education = "Test education";
        StaffMember member = new StaffMember(name, address, workStart, education);
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        memberDAO.addStaffMember(member);
        StaffMember loadedMember = memberDAO.getStaffMemberById(member.getId());
        Assert.assertTrue(member.my_equals(loadedMember));
    }

    @Test
    public void updateStaffMember() throws SQLException {
        String name = "Test member name";
        String address = "Test address";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 10, 1, 0, 0, 0);
        Date workStart = calendar.getTime();
        String education = "Test education";
        StaffMember member = new StaffMember(name, address, workStart, education);
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        memberDAO.addStaffMember(member);
        StaffMember newMember = new StaffMember(name + "2", address + "2", workStart, education + "2");
        newMember.setId(member.getId());
        memberDAO.updateStaffMember(newMember);
        StaffMember loadedMember = memberDAO.getStaffMemberById(member.getId());
        Assert.assertTrue(newMember.my_equals(loadedMember));
    }

    @Test
    public void deleteStaffMember() throws SQLException {
        StaffMember member = new StaffMember();
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        memberDAO.addStaffMember(member);
        memberDAO.deleteStaffMember(member);
        Collection<StaffMember> members = memberDAO.getAllStaffMembers();
        for (StaffMember listMember : members) {
            Assert.assertFalse(member.my_equals(listMember));
        }
    }

    @Test
    public void getStaffMembersByDepartment() throws SQLException {
        Department department = new Department();
        ArrayList<StaffMember> members = new ArrayList<StaffMember>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
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
        Collection<StaffMember> loadedMembers = staffMemberDAO.getStaffMembersByDepartment(department);
        Assert.assertTrue(loadedMembers.size() == members.size());
        for (StaffMember mem : members) {
            boolean founded = false;
            for (StaffMember loadedMem : loadedMembers) {
                if (loadedMem.my_equals(mem)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }

    @Test
    public void getStaffMembersByDepartmentNone() throws SQLException {
        Department department = new Department();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        departmentDAO.addDepartment(department);
        StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
        Collection<StaffMember> members = memberDAO.getStaffMembersByDepartment(department);
        Assert.assertTrue(members.size() == 0);
    }

    @Test
    public void getStaffMembersByPosition() throws SQLException {
        Department department = new Department();
        ArrayList<StaffMember> members = new ArrayList<StaffMember>();
        DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
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
        Collection<StaffMember> loadedMembers = staffMemberDAO.getStaffMembersByPosition(position);
        Assert.assertTrue(loadedMembers.size() == members.size());
        for (StaffMember mem : members) {
            boolean founded = false;
            for (StaffMember loadedMem : loadedMembers) {
                if (loadedMem.my_equals(mem)) {
                    founded = true;
                }
            }
            Assert.assertTrue(founded);
        }
    }

    @Test
    public void getStaffMembersByPositionNone() throws SQLException {
        Position position = new Position();
        PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
        positionDAO.addPosition(position);
        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        Collection<StaffMember> members = staffMemberDAO.getStaffMembersByPosition(position);
        Assert.assertTrue(members.size() == 0);
    }

    @Test
    public void getStaffMembersyName() throws SQLException {
        StaffMember memA = new StaffMember();
        StaffMember memB = new StaffMember();
        StaffMember memC = new StaffMember();

        memA.setName("A_mem");
        memB.setName("mem_B");
        memC.setName("Vasya");

        StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
        staffMemberDAO.addStaffMember(memA);
        staffMemberDAO.addStaffMember(memB);
        staffMemberDAO.addStaffMember(memC);

        Collection<StaffMember> members = staffMemberDAO.getStaffMembersByName("mem");
        Assert.assertTrue(members.size() >= 2); // there can be more names with mem already
        for (StaffMember mem : members) {
            Assert.assertTrue(mem.getName().toLowerCase().contains("mem"));
        }
    }


}
