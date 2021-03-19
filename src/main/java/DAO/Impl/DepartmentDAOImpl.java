package DAO.Impl;

import DAO.DepartmentDAO;
import logic.Department;
import logic.StaffMember;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DepartmentDAOImpl implements DepartmentDAO {
    public void addDepartment(Department department) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(department);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateDepartment(Department department) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(department);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Department getDepartmentById(Long department_id) throws SQLException {
        Session session = null;
        Department department = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        department = (Department) session.load(Department.class, department_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return department;
    }

    public Collection<Department> getAllDepartments() throws SQLException {
        Session session = null;
        List<Department> departments = new ArrayList<Department>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Department> query = session.createQuery("select d from Department d", Department.class);
        departments = (List<Department>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return departments;
    }

    public void deleteDepartment(Department department) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(department);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Collection<Department> getDepartmentsByDirector(StaffMember director) throws SQLException {
        Session session = null;
        List<Department> departments = new ArrayList<Department>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Department> query = session.createQuery("from Department WHERE director = :director_id", Department.class);
        query.setParameter("director_id", director);
        departments = (List<Department>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return departments;
    }
}

