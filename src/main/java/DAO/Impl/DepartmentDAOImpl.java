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
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in addDepartment: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateDepartment(Long department_id, Department department) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in department update: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();

            }
        }
    }

    public Department getDepartmentById(Long department_id) throws SQLException {
        Session session = null;
        Department department = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            department = (Department) session.load(Department.class, department_id);
        } catch (Exception e) {
            System.err.println("Error in findByID: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return department;
    }

    public Collection getAllDepartments() throws SQLException {
        Session session = null;
        List departments = new ArrayList<Department>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select d from Department d");
            departments = (List<Department>)query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in getAllDepartments: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return departments;
    }

    public void deleteDepartment(Department department) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in delete department: "+ e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getDepartmentsByDirector(StaffMember director) throws SQLException {
        Session session = null;
        List departments = new ArrayList<Department>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Long director_id = director.getId();
            Query query = session.createQuery(
                    "select d "
                        + " from Director d INNER JOIN d.departments department"
                        + " where department.id = :directorId "
                    );
            departments = (List<Department>)query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in get Departments by director: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return departments;
    }
}

