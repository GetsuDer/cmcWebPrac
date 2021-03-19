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
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(employee);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Employee getEmployeeById(Long employee_id) throws SQLException {
        Session session = null;
        Employee employee = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        employee = (Employee) session.load(Employee.class, employee_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return employee;
    }

    public Collection<Employee> getAllEmployees() throws SQLException {
        Session session = null;
        List<Employee> employees = new ArrayList<Employee>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("select e from Employee e", Employee.class);
        employees = (List<Employee>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return employees;
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Collection<Employee> getEmployeesByStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        List<Employee> employees = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("from Employee WHERE staffMember = :member_id", Employee.class);
        query.setParameter("member_id", member);
        employees = (List<Employee>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return employees;
    }

    public Collection<Employee> getEmployeesByPosition(Position position) throws SQLException {
        Session session = null;
        List<Employee> employees = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Employee> query = session.createQuery("from Employee WHERE position = :position_id", Employee.class);
        query.setParameter("position_id", position);
        employees = (List<Employee>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return employees;
    }
}
