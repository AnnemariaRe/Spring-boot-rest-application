package org.annemariare.kotiki.converter;

import org.annemariare.kotiki.dto.UserDto;
import org.annemariare.kotiki.entity.UserEntity;

public class UserConverter {
    public static UserEntity toUser(UserDto dto) {

        return new UserEntity(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getRole(), OwnerConverter.dtoToEntity(dto.getOwner()));
    }

    public static UserDto fromUser(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setOwner(OwnerConverter.entityToDto(user.getOwner()));
        userDto.setRole(user.getRole());

        return userDto;
    }
}
