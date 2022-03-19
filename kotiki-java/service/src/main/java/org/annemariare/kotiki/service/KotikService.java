package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dao.KotikDaoImpl;
import org.annemariare.kotiki.model.Kotik;

import java.sql.SQLException;
import java.util.List;

public class KotikService {
    private final KotikDaoImpl kotikDao = new KotikDaoImpl();

    public void addKotik(Kotik kotik) {
        kotikDao.add(kotik);
    }

    public void updateKotik(Kotik kotik) {
        kotikDao.update(kotik);
    }

    public void deleteKotik(Kotik kotik) {
        kotikDao.delete(kotik);
    }

    public Kotik findKotik(int id) throws SQLException {
        return kotikDao.findById(id);
    }

    public List<Kotik> findAllKotiki() throws SQLException {
        return kotikDao.findAll();
    }
}
