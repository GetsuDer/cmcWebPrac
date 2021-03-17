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
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Position add error");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updatePosition(Position position) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Position update error");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Position getPositionById(Long position_id) throws SQLException {
        Session session = null;
        Position position = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            position = (Position) session.load(Position.class, position_id);
        } catch (Exception e) {
            System.out.println("Error in position get");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return position;
    }

    public Collection getAllPositions() throws SQLException {
        Session session = null;
        List positions = new ArrayList<Position>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select p from Position p");
            positions = (List<Position>)query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in position get all");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return positions;
    }

    public void deletePosition(Position position) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in position delete");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getPositionsByDepartment(Department department) throws SQLException {
        Session session = null;
        List positions = new ArrayList<Position>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Position WHERE department = :department_id");
            query.setParameter("department_id", department);
            positions = (List<Position>)query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in get position by department");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return positions;
    }

    public Collection getPositionsByStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        Set positions = new HashSet<Position>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            
            Query query = session.createQuery("from Employee WHERE staff_member = :member_id");
            query.setParameter("member_id", member);
            List<Employee> employees = (List<Employee>)query.list();
            for (Employee emp : employees) {
                query = session.createQuery("from Position WHERE position_id = :position_ID");
                query.setParameter("position_ID", emp.getPosition().getId());
                List<Position> partPositions = (List<Position>)query.list();
                for (Position pos : partPositions) {
                    positions.add(pos);
                }
            }           
            
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in get positions by staff member");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return positions;
    }    
}
