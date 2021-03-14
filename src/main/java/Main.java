import java.io.*;
import logic.*;
import DAO.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String args[]) throws SQLException {
        System.out.println("Hello");
        Collection departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
        Iterator iterator = departments.iterator();
        while (iterator.hasNext()) {
            Department department = (Department) iterator.next();
            System.out.println("Department: " + department.getName());
        }
    }
}    
