package org.annemariare.cats.dao;

import org.annemariare.cats.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<OwnerEntity, Integer> {
    OwnerEntity findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    OwnerEntity findByName(String name);
}
