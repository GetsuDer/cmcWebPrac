package DAO.Impl;

import DAO.DepartmentDAO;
import logic.Department;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;

public class DepartmentDAOImpl implements DepartmentDAO {
    public void addDepartment(Department department) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in addDepartment");
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
            System.out.println("Error in department update");
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
            System.out.println("Error findByID");
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
            departments = session.createCriteria(Department.class).list();
        } catch (Exception e) {
            System.out.println("Error in getAllDepartments");
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
            System.out.println("Error in delete department");
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
                    ).setLong("directorId", director_Id);
            departments = (List<Department>)query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return departments;
    }
}

