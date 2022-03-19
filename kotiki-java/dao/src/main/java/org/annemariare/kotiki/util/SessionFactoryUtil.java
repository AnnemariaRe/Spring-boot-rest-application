package org.annemariare.kotiki.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private Session session;
    private Transaction transaction;

    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void openTransactionSession() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        session.close();
    }

    public void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
