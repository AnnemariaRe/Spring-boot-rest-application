package org.annemariare.kotiki.converter;

import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.entity.OwnerEntity;

import java.util.ArrayList;

public class OwnerConverter {
    public static OwnerDto entityToDto(OwnerEntity entity) {
        return new OwnerDto(entity);
    }

    public static OwnerEntity dtoToEntity(OwnerDto dto) {
        return new OwnerEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), new ArrayList<>());
    }
}
