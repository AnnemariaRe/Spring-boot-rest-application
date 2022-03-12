package ru.itmo.kotiki.service;

import ru.itmo.kotiki.dao.OwnerDaoImpl;
import ru.itmo.kotiki.model.Owner;

import java.sql.SQLException;
import java.util.List;

public class OwnerService {
    private final OwnerDaoImpl ownerDao = new OwnerDaoImpl();

    public void addOwner(Owner owner) {
        ownerDao.add(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public Owner findOwner(int id) throws SQLException {
        return ownerDao.findById(id);
    }

    public List<Owner> findAllOwners() throws SQLException {
        return ownerDao.findAll();
    }
}
