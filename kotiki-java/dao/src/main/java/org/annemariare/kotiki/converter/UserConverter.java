package org.annemariare.kotiki.converter;

import org.annemariare.kotiki.dto.UserDto;
import org.annemariare.kotiki.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static UserEntity toUser(UserDto dto) {
        return new UserEntity(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getRole(), OwnerConverter.dtoToEntity(dto.getOwner()));
    }

    public static UserDto fromUser(UserEntity user) {

        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), OwnerConverter.entityToDto(user.getOwner()), user.getRole());
    }
}
