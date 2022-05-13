package org.annemariare.kotiki.dao;

import org.annemariare.kotiki.entity.KotikEntity;
import org.annemariare.kotiki.enums.Color;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface KotikRepo extends JpaRepository<KotikEntity, Integer> {
    KotikEntity findById(Long id);
    KotikEntity findByName(String name);
    List<KotikEntity> findByBreed(String breed);
    List<KotikEntity> findByColor(Color color);
    void deleteById(Long id);
    boolean existsById(Long id);
}
