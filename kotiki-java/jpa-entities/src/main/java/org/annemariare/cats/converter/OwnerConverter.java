package org.annemariare.cats.converter;

import org.annemariare.cats.dto.OwnerDto;
import org.annemariare.cats.entity.OwnerEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OwnerConverter {
    public OwnerDto entityToDto(OwnerEntity entity) {
        return new OwnerDto(entity);
    }

    public OwnerEntity dtoToEntity(OwnerDto dto) {
        return new OwnerEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), new ArrayList<>());
    }
}
