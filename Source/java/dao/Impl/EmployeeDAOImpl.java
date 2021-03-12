package DAO.Impl;

import logic.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

    public void addEmployee(Employee employee) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in E add");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateEmployee(Long employee_id, Employee employee) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.uupdate(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in e update");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Employee getEmployeeById(Long employee_id) throws SQLException {
        Session session = null;
        Employee employee = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            employee = (Employee) session.load(Employee.class, employee_id);
        } catch (Exception e) {
            System.out.println("Error in e get by id");
        } finnaly {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employee;
    }

    public Collection getAllEmployees() throws SQLException {
        Session session = null;
        List employees = new ArrayList<Employee>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            employees = session.createCriteria(Employee.class).list();
        } catch (Exception e) {
            System.out.println("Error in E get all");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in E delete");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
