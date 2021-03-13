package util;

import logic.Department;
import logic.StaffMember;
import logic.Position;
import logic.Employee;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateSessionFactoryUtil {

    public static SessionFactory factory;

    private HibernateSessionFactoryUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return factory;
    }
}
