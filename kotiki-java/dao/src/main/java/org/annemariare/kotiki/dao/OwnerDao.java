package org.annemariare.kotiki.dao;

import org.annemariare.kotiki.model.Owner;

import java.sql.SQLException;
import java.util.List;

public interface OwnerDao {
    void add(Owner owner);

    void update(Owner owner);

    void delete(Owner owner);

    Owner findById(int id) throws SQLException;

    List<Owner> findAll() throws SQLException;
}
