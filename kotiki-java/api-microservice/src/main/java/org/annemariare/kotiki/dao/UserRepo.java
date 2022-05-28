package org.annemariare.kotiki.dao;

import org.annemariare.kotiki.entity.UserEntity;
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
