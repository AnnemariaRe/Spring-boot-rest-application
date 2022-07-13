package org.annemariare.cats.dao;

import org.annemariare.cats.entity.CatEntity;
import org.annemariare.cats.entity.OwnerEntity;
import org.annemariare.cats.enums.Color;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CatRepo extends JpaRepository<CatEntity, Integer> {
    List<CatEntity> findAll();
    List<CatEntity> findAllByOwner(OwnerEntity owner);
    CatEntity findById(Long id);
    CatEntity findByName(String name);
    List<CatEntity> findByBreed(String breed);
    List<CatEntity> findByColor(Color color);
    void deleteById(Long id);
    boolean existsById(Long id);
}
