package org.annemariare.kotiki.dto;

import org.annemariare.kotiki.entity.OwnerEntity;

import java.util.ArrayList;

public class OwnerConverter {
    public static OwnerDto entityToDto(OwnerEntity entity) {
        OwnerDto dto = new OwnerDto(entity);
        return dto;
    }

    public static OwnerEntity dtoToEntity(OwnerDto dto) {
        OwnerEntity entity = new OwnerEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), new ArrayList<>());
        return entity;
    }
}
