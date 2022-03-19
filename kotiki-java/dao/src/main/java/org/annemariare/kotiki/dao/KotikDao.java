package org.annemariare.kotiki.dao;

import org.annemariare.kotiki.model.Kotik;

import java.sql.SQLException;
import java.util.List;

public interface KotikDao {
    void add(Kotik kotik);

    void update(Kotik kotik);

    void delete(Kotik kotik);

    Kotik findById(int id) throws SQLException;

    List<Kotik> findAll() throws SQLException;
}
