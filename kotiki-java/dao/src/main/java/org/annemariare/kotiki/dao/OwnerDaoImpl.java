package org.annemariare.kotiki.dao;

import org.hibernate.Session;
import org.annemariare.kotiki.model.Owner;
import org.annemariare.kotiki.util.SessionFactoryUtil;

import java.util.List;

public class OwnerDaoImpl extends SessionFactoryUtil implements OwnerDao {
    @Override
    public void add(Owner owner) {
        openTransactionSession();

        Session session = getSession();
        session.save(owner);

        closeTransactionSession();
    }

    @Override
    public void update(Owner owner) {
        openTransactionSession();

        Session session = getSession();
        session.update(owner);

        closeTransactionSession();
    }

    @Override
    public void delete(Owner owner) {
        openTransactionSession();

        Session session = getSession();
        session.delete(owner);

        closeTransactionSession();
    }

    @Override
    public Owner findById(int id) {
        return getSessionFactory()
                .openSession()
                .get(Owner.class, id);
    }

    @Override
    public List<Owner> findAll() {
        return getSessionFactory()
                .openSession()
                .createQuery("select o from Owner o", Owner.class)
                .getResultList();
    }
}
