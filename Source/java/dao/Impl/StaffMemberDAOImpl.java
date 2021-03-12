package DAO.Impl;

import logic.StaffMember;

public class StaffMemberDAOImpl implements StaffMemberDAO {
    public void addStaffMember(StaffMember member) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(member);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in M add");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    public void updateStaffMember(Long member_id, StaffMember member) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil().openSession();
            session.beginTransaction();
            session.update(member);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in M update");
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
            session = HibernateSessionFactoryUtil().getSessionFactory().openSession();
            member = (StaffMember) session.load(StaffMember.class, member_id);
        } catch (Exception e) {
            System.out.println("Error in get M");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return member;
    }

    public Collection getAllStaffMembers() throw SQLException {
        Session session = null;
        List members = new ArrayList<StaffMember>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            members = session.createCriteria(StaffMember.class).list();
        } catch (Exception e) {
            System.out.println("Error in m get all");
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
            System.out.println("Error in M delete");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getStaffMembersByDepartment(Department department) throws SQLException {
        Session session = null;
        List members = new ArrayList<StaffMember>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long department_id = department.getId();
            Query query = session.createQuery(
                    "select m"
                        + " from StaffMembers m INNER JOIN m.departments department"
                        + " where department.id = :departmentId"
                    ).setLong("departmentId", department_id);
            members = (List<StaffMember>) query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in M get m by d");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return members;
    }

    public Collection getStaffMembersByPosition(Position position) {
        Session session = null;
        List members = new ArrayList<StaffMember>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long position_id = position.getId();
            Query query = session.createQuery(
                    "select m "
                        + " from StaffMembers m INER JOIN m.positions position"
                        + " where position.id = :positionId"
                    ).setLong("positionId", position_id);
            members = (List<StaffMember>) query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error in get m by position");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return members;
    }
}
