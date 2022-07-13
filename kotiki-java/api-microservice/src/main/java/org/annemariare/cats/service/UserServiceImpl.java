package org.annemariare.cats.service;

import org.annemariare.cats.converter.UserConverter;
import org.annemariare.cats.dao.UserRepo;
import org.annemariare.cats.dto.UserDto;
import org.annemariare.cats.entity.UserEntity;
import org.annemariare.cats.enums.Role;
import org.annemariare.cats.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepository;
    private final UserConverter convert;

    @Autowired
    public UserServiceImpl(UserRepo userRepository, UserConverter convert) {
        this.userRepository = userRepository;
        this.convert = convert;
    }

    @Override
    public void register(UserDto user) {
        var entity = convert.toUser(user);
        entity.setRole(Role.ROLE_USER);
        userRepository.save(entity);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDto> dto = new ArrayList<>();
        for (var entity : users) {
            dto.add(convert.fromUser(entity));
        }

        return dto;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
