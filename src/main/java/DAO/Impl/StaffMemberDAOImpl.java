package DAO.Impl;

import logic.*;
import DAO.StaffMemberDAO;
import util.HibernateSessionFactoryUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class StaffMemberDAOImpl implements StaffMemberDAO {
    public void addStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(member);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("addStaffMember: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public void updateStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(member);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("updateStaffMember: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public StaffMember getStaffMemberById(Long member_id) throws SQLException {
        Session session = null;
        StaffMember member = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            member = (StaffMember) session.load(StaffMember.class, member_id);
        } catch (Exception e) {
            System.err.println("getStaffMemberById: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return member;
    }

    public Collection getAllStaffMembers() throws SQLException {
        Session session = null;
        List<StaffMember> members = new ArrayList<StaffMember>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select m from StaffMember m");
            members = (List<StaffMember>)query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("getAllStaffMembers: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return members;
    }

    public void deleteStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(member);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("deleteStaffMember: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getStaffMembersByDepartment(Department department) throws SQLException {
        Session session = null;
        Set members = new HashSet<StaffMember>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            
            // department positions
            Query positionsQuery = session.createQuery("from Position WHERE department = :department_id");
            positionsQuery.setParameter("department_id", department);
            List<Position> positions = (List<Position>) positionsQuery.list();
            for (Position pos : positions) {
                // employees for current position
                Query employeesQuery = session.createQuery("from Employee WHERE position = :position_id");
                employeesQuery.setParameter("position_id", pos);
                List<Employee> employees = (List<Employee>) employeesQuery.list();
                
                for (Employee emp: employees) {
                    // members (in fact - member) for current employee
                    Query membersQuery = session.createQuery("from StaffMember WHERE id = :member_id");
                    membersQuery.setParameter("member_id", emp.getStaffMember().getId());
                    List<StaffMember> partMembers = (List<StaffMember>) membersQuery.list();
                    
                    for (StaffMember mem : partMembers) {
                        members.add(mem);
                    }
                } 
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("getStaffMembersByDepartment: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return members;
    }

    public Collection getStaffMembersByPosition(Position position) {
        Session session = null;
        Set members = new HashSet<StaffMember>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Query employeesQuery = session.createQuery("from Employee WHERE position = :position_id");
            employeesQuery.setParameter("position_id", position);
            List<Employee> employees = (List<Employee>) employeesQuery.list();
            for (Employee emp : employees) {
                    Query membersQuery = session.createQuery("from StaffMember WHERE id = :member_id");
                    membersQuery.setParameter("member_id", emp.getStaffMember().getId());
                    List<StaffMember> partMembers = (List<StaffMember>) membersQuery.list();
                    for (StaffMember mem : partMembers) {
                        members.add(mem);
                    }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("getStaffMembersByPosition: " + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return members;
    }
}
