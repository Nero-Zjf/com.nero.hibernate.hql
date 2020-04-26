package basic.sample.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate工具类
 *
 * @created nero
 * @date 2018/10/10 11:23
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration cfg = new Configuration().configure();
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties())
                    .build();
            return cfg.buildSessionFactory(registry);

//            此方法已抛弃
//            return new Configuration().configure().buildSessionFactory(
//                    new StandardServiceRegistryBuilder().build());
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
