package DAO;
import DAO.*;
import DAO.Impl.*;

public class Factory {

    private static DepartmentDAO departmentDAO = null;
    private static PositionDAO positionDAO = null;
    private static StaffMemberDAO staffMemberDAO = null;
    private static EmployeeDAO employeeDAO = null;

    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public DepartmentDAO getDepartmentDAO() {
        if (departmentDAO == null) {
            departmentDAO = new DepartmentDAOImpl();
        }
        return departmentDAO;
    }

    public PositionDAO getPositionDAO() {
        if (positionDAO == null) {
            positionDAO = new PositionDAOImpl();
        }
        return positionDAO;
    }

    public StaffMemberDAO getStaffMemberDAO() {
        if (staffMemberDAO == null) {
            staffMemberDAO = new StaffMemberDAOImpl();
        }
        return staffMemberDAO;
    }

    public EmployeeDAO getEmployeeDAO() {
        if (employeeDAO == null) {
            employeeDAO = new EmployeeDAOImpl();
        }
        return employeeDAO;
    }
}
