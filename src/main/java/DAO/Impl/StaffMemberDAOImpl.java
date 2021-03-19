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
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(member);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
    
    public void updateStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(member);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public StaffMember getStaffMemberById(Long member_id) throws SQLException {
        Session session = null;
        StaffMember member = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        member = (StaffMember) session.load(StaffMember.class, member_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return member;
    }

    public Collection<StaffMember> getAllStaffMembers() throws SQLException {
        Session session = null;
        List<StaffMember> members = new ArrayList<StaffMember>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<StaffMember> query = session.createQuery("select m from StaffMember m", StaffMember.class);
        members = (List<StaffMember>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return members;
    }

    public void deleteStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(member);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Collection<StaffMember> getStaffMembersByDepartment(Department department) throws SQLException {
        Session session = null;
        Set<StaffMember> members = new HashSet<StaffMember>();
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
            
            // department positions
        Query<Position> positionsQuery = session.createQuery("from Position WHERE department = :department_id", Position.class);
        positionsQuery.setParameter("department_id", department);
        List<Position> positions = (List<Position>) positionsQuery.list();
        for (Position pos : positions) {
            // employees for current position
            Query<Employee> employeesQuery = session.createQuery("from Employee WHERE position = :position_id", Employee.class);
            employeesQuery.setParameter("position_id", pos);
            List<Employee> employees = (List<Employee>) employeesQuery.list();
                
            for (Employee emp: employees) {
                // members (in fact - member) for current employee
                Query<StaffMember> membersQuery = session.createQuery("from StaffMember WHERE id = :member_id", StaffMember.class);
                membersQuery.setParameter("member_id", emp.getStaffMember().getId());
                List<StaffMember> partMembers = (List<StaffMember>) membersQuery.list();
                
                for (StaffMember mem : partMembers) {
                    members.add(mem);
                }
            } 
        }
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return members;
    }

    public Collection<StaffMember> getStaffMembersByPosition(Position position) {
        Session session = null;
        Set<StaffMember> members = new HashSet<StaffMember>();
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query<Employee> employeesQuery = session.createQuery("from Employee WHERE position = :position_id", Employee.class);
        employeesQuery.setParameter("position_id", position);
        List<Employee> employees = (List<Employee>) employeesQuery.list();
        for (Employee emp : employees) {
                Query<StaffMember> membersQuery = session.createQuery("from StaffMember WHERE id = :member_id", StaffMember.class);
                membersQuery.setParameter("member_id", emp.getStaffMember().getId());
                List<StaffMember> partMembers = (List<StaffMember>) membersQuery.list();
                for (StaffMember mem : partMembers) {
                    members.add(mem);
                }
        }
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return members;
    }
}
