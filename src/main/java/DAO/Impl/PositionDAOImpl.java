package DAO.Impl;

import DAO.PositionDAO;
import logic.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PositionDAOImpl implements PositionDAO {
    public void addPosition(Position position) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(position);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
             session.close();
        }
    }

    public void updatePosition(Position position) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(position);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Position getPositionById(Long position_id) throws SQLException {
        Session session = null;
        Position position = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        position = (Position) session.load(Position.class, position_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return position;
    }

    public Collection<Position> getAllPositions() throws SQLException {
        Session session = null;
        List<Position> positions = new ArrayList<Position>();
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Position> query = session.createQuery("select p from Position p", Position.class);
        positions = (List<Position>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return positions;
    }

    public void deletePosition(Position position) throws SQLException {
        Session session = null;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(position);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Collection<Position> getPositionsByDepartment(Department department) throws SQLException {
        Session session = null;
        List<Position> positions = new ArrayList<Position>();
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Position> query = session.createQuery("from Position WHERE department = :department_id", Position.class);
        query.setParameter("department_id", department);
        positions = (List<Position>)query.list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return positions;
    }

    public Collection<Position> getPositionsByStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        Set<Position> positions = new HashSet<Position>();
        session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
           
        Query<Employee> query = session.createQuery("from Employee WHERE staff_member = :member_id", Employee.class);
        query.setParameter("member_id", member);
        List<Employee> employees = (List<Employee>)query.list();
        for (Employee emp : employees) {
            Query<Position> queryPosition = session.createQuery("from Position WHERE position_id = :position_ID", Position.class);
            queryPosition.setParameter("position_ID", emp.getPosition().getId());
            List<Position> partPositions = (List<Position>)queryPosition.list();
            for (Position pos : partPositions) {
                positions.add(pos);
            }
        }           
            
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return positions;
    }    
}
