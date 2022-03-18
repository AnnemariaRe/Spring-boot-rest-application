package org.annemariare.kotiki.dao;

import org.hibernate.Session;
import org.annemariare.kotiki.model.Kotik;
import org.annemariare.kotiki.util.SessionFactoryUtil;

import java.util.List;

public class KotikDaoImpl extends SessionFactoryUtil implements KotikDao {
    @Override
    public void add(Kotik kotik) {
        openTransactionSession();

        Session session = getSession();
        session.save(kotik);

        closeTransactionSession();
    }

    @Override
    public void update(Kotik kotik) {
        openTransactionSession();

        Session session = getSession();
        session.update(kotik);

        closeTransactionSession();
    }

    @Override
    public void delete(Kotik kotik) {
        openTransactionSession();

        Session session = getSession();
        session.delete(kotik);

        closeTransactionSession();
    }

    @Override
    public Kotik findById(int id) {
        return getSessionFactory()
                .openSession()
                .get(Kotik.class, id);
    }

    @Override
    public List<Kotik> findAll() {
        return getSessionFactory()
                .openSession()
                .createQuery("select k from Kotik k", Kotik.class)
                .getResultList();
    }
}
