package DAO.Impl;

import DAO.PositionDAO;
import logic.Position;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import util.HibernateFactorySessionUtil;
import org.hibernate.Session;
import org.hibernate.Query;

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

    public void updatePosition(Long position_id, Position position) throws SQLException {
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
            positions = session.createCriteria(Position.class).list();
        } catch (Exception e) {
            System.out.println("Error in position get all");
        } finally {
            if (session != null && session.isOpen) {
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

    public Collection getPositionsByDepartment(Department departments) throws SQLException {
        Session session = null;
        List positions = new ArrayList<Positions>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long department_id = department.getId();
            Query query = session.createQuery(
                    "select p"
                        + " from Position p INNER JOIN p.departments department"
                        + " where department.id = :departmentId"
                    ).setLong("departmentId", department_id);
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
        List positions = new ArrayList<Positions>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long member_id = member.getId();
            Query query = session.createQuery (
                    "select p"
                        + " from Position p INNER JOIN p.staff_members member"
                        + " where member.id = :memberId"
                    ).setLong("memberId", member_id);
            positions = (List<Position>)query.list();
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
