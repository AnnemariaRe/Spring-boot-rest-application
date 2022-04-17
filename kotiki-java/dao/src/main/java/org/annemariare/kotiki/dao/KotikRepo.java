package org.annemariare.kotiki.dao;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.entity.KotikEntity;
import org.annemariare.kotiki.enums.Color;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface KotikRepo extends JpaRepository<KotikEntity, Integer> {
    KotikEntity findById(Long id);
    KotikEntity findByName(String name);
    KotikEntity findByBreed(String breed);
    KotikEntity findByColor(Color color);
    KotikEntity deleteById(Long id);
    boolean existsById(Long id);
}
