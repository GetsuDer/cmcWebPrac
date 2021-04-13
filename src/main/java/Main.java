import java.io.*;
import logic.*;
import DAO.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import web.ServingWebContentApplication;

public class Main {
    public static void main(String args[]) throws SQLException {
   /*     System.out.println("======= Departments ===========");
        Collection departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
        Iterator iterator = departments.iterator();
        while (iterator.hasNext()) {
            Department department = (Department) iterator.next();
            System.out.println(department);
        }

        System.out.println("======= Positions ===========");
        Collection positions = Factory.getInstance().getPositionDAO().getAllPositions();
        iterator = positions.iterator();
        while (iterator.hasNext()) {
            Position position = (Position) iterator.next();
            System.out.println(position);
        }

        System.out.println("======= StaffMembers ========");
        Collection members = Factory.getInstance().getStaffMemberDAO().getAllStaffMembers();
        iterator = members.iterator();
        while (iterator.hasNext()) {
            StaffMember member = (StaffMember) iterator.next();
            System.out.println(member);
        }

        System.out.println("======= Employees ========");
        Collection employees = Factory.getInstance().getEmployeeDAO().getAllEmployees();
        iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            System.out.println(employee);
        }*/
        ServingWebContentApplication.main(args);
    }
}
