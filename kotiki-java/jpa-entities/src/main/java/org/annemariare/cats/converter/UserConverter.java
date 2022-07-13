package org.annemariare.cats.converter;

import org.annemariare.cats.dto.UserDto;
import org.annemariare.cats.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private OwnerConverter convert;

    public UserEntity toUser(UserDto dto) {
        return new UserEntity(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getRole(), convert.dtoToEntity(dto.getOwner()));
    }

    public UserDto fromUser(UserEntity user) {

        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), convert.entityToDto(user.getOwner()), user.getRole());
    }
}
