package infrastructure;

import feature.book.model.Book;
import feature.user.model.User;
import feature.loan.model.Loan;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseManager {

    private static SessionFactory sessionFactory;

    public static SessionFactory getDatabaseSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Loan.class)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("There was an issue building the SessionFactory", e);
        }
    }
}
