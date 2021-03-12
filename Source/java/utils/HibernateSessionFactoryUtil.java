package utils;

import logic.Department;
import logic.Staff_Member;
import logic.Position;
import logic.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Department.class);
                configuration.addAnnotatedClass(Staff_Member.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Position.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Exception " + e);
            }
        }
        return sessionFactory;
    }
}
