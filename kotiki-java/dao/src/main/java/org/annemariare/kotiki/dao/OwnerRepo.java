package org.annemariare.kotiki.dao;

import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepo extends JpaRepository<OwnerEntity, Integer> {
    OwnerEntity findById(Long id);
    OwnerEntity findByName(String name);
    OwnerEntity deleteById(Long id);
    boolean existsById(Long id);
}
