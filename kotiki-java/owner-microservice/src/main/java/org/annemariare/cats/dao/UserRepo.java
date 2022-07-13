package org.annemariare.cats.dao;

import org.annemariare.cats.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    Optional<UserEntity> findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
}
