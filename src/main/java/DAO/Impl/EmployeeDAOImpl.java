package DAO.Impl;

import logic.*;
import DAO.*;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public void updateEmployee(Employee employee) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(employee);
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
        } finally {
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
            session.beginTransaction();
            Query query = session.createQuery("select e from Employee e");
            employees = (List<Employee>)query.list();
            session.getTransaction().commit();
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

    public Collection getEmployeesByStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        List<Employee> employees = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Employee WHERE staffMember = :member_id");
            query.setParameter("member_id", member);
            employees = (List<Employee>)query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("getEmployeesByStaffMember: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }

    public Collection getEmployeesByPosition(Position position) throws SQLException {
        Session session = null;
        List<Employee> employees = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Employee WHERE position = :position_id");
            query.setParameter("position_id", position);
            employees = (List<Employee>)query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("getEmployeesByStaffMember: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }
}
