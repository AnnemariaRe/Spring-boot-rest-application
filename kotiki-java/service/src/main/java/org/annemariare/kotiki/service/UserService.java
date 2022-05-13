package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dto.UserDto;
import org.annemariare.kotiki.entity.UserEntity;

import java.util.List;

public interface UserService {
    void register(UserDto user);

    List<UserDto> getAll();

    UserEntity findByUsername(String username);

    UserDto findById(Long id);

    void delete(Long id);
}
