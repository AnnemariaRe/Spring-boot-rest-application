package org.annemariare.cats.service;

import org.annemariare.cats.dto.UserDto;
import org.annemariare.cats.entity.UserEntity;

import java.util.List;

public interface UserService {
    void register(UserDto user);

    List<UserDto> getAll();

    UserEntity findByUsername(String username);

    UserDto findById(Long id);

    void delete(Long id);
}
