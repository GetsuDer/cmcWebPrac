import java.io.*;

import logic.*;
import DAO.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class Main {
    public static void main(String args[]) throws SQLException, ParseException {
        boolean db_init = false;
        for (String s : args) {
            if (s.equals("init")) {
                db_init = true;
            }
        }
        if (db_init) {
            DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
            PositionDAO positionDAO = Factory.getInstance().getPositionDAO();
            StaffMemberDAO memberDAO = Factory.getInstance().getStaffMemberDAO();
            EmployeeDAO employeeDAO = Factory.getInstance().getEmployeeDAO();
            
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            StaffMember member1 = new StaffMember("Ivanov Ivan Ivanovich", "Address1", format.parse("2014-11-04"), "MSU");
            memberDAO.addStaffMember(member1);
            StaffMember member2 = new StaffMember("Petrov Petr Petrovich", "Address2", format.parse("2015-1-06"), "MSU");
            memberDAO.addStaffMember(member2);
            StaffMember member3 = new StaffMember("Sidorov Sidor Sidorovich", "Address3", format.parse("2016-9-08"), "MSU");
            memberDAO.addStaffMember(member3);
            StaffMember member4 = new StaffMember("Ivanov Petr Ivanovich", "Address4", format.parse("2020-3-14"), "MSU");
            memberDAO.addStaffMember(member4);
            StaffMember member5 = new StaffMember("Ivanov Sidor Ivanovich", "Address5", format.parse("2012-4-24"), "VSE");
            memberDAO.addStaffMember(member5);
            StaffMember member6 = new StaffMember("Petrov Ivan Ivanovich", "Address6", format.parse("2009-8-05"), "MSU");
            memberDAO.addStaffMember(member6);
            StaffMember member7 = new StaffMember("Sidorov Ivan Ivanovich", "Address7", format.parse("2008-6-09"), "MSU");
            memberDAO.addStaffMember(member7);
            StaffMember member8 = new StaffMember("Ivanov Ivan Sidorovich", "Address8", format.parse("2009-2-08"), "MSU");
            memberDAO.addStaffMember(member8);
            StaffMember member9 = new StaffMember("Ivanov Ivan Petrovich", "Address9", format.parse("2003-6-15"), "MSU");
            memberDAO.addStaffMember(member9);
            StaffMember member10 = new StaffMember("Ivanov Alex Ivanovich", "Address10", format.parse("2012-5-16"), "MSU");
            memberDAO.addStaffMember(member10);
            StaffMember member11 = new StaffMember("Ivanova Maria Ivanovna", "Address11", format.parse("2014-4-16"), "MSU");
            memberDAO.addStaffMember(member11);
    
            Department dep1 = new Department("dep1", null, member1);
            departmentDAO.addDepartment(dep1);
            Department dep2 = new Department("dep2", dep1, member2);
            departmentDAO.addDepartment(dep2);
            Department dep3 = new Department("dep3", dep1, member3);
            departmentDAO.addDepartment(dep3);
            Department dep4 = new Department("dep4", dep2, member4);
            departmentDAO.addDepartment(dep4);
            Department dep5 = new Department("dep5", dep2, member5);
            departmentDAO.addDepartment(dep5);

            Position pos1 = new Position("pos1", "smth", dep1, Long.parseLong("2"));
            positionDAO.addPosition(pos1);
            Position pos2 = new Position("pos2", "smth", dep1, Long.parseLong("1"));
            positionDAO.addPosition(pos2);
            Position pos3 = new Position("pos3", "smth", dep1, Long.parseLong("1"));
            positionDAO.addPosition(pos3);
            Position pos4 = new Position("pos4", "smth", dep2, Long.parseLong("1"));
            positionDAO.addPosition(pos4);
            Position pos5 = new Position("pos5", "smth", dep3, Long.parseLong("1"));
            positionDAO.addPosition(pos5);
            Position pos6 = new Position("pos6", "smth", dep4, Long.parseLong("1"));
            positionDAO.addPosition(pos6);
            Position pos7 = new Position("pos7", "smth", dep5, Long.parseLong("1"));
            positionDAO.addPosition(pos7);
        
            Employee emp1 = new Employee(pos1, member1, format.parse("2014-11-04"), null);
            employeeDAO.addEmployee(emp1);
            Employee emp2 = new Employee(pos1, member2, format.parse("2015-1-06"), null);
            employeeDAO.addEmployee(emp2);
            Employee emp3 = new Employee(pos2, member3, format.parse("2016-9-11"), null);
            employeeDAO.addEmployee(emp3);
            Employee emp4 = new Employee(pos4, member4, format.parse("2020-3-14"), null);
            employeeDAO.addEmployee(emp4);
            Employee emp5 = new Employee(pos5, member5, format.parse("2012-4-29"), null);
            employeeDAO.addEmployee(emp5);
        
        }
    }
}
