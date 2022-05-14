package org.annemariare.kotiki.converter;

import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.entity.OwnerEntity;
import org.springframework.context.annotation.Bean;
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
