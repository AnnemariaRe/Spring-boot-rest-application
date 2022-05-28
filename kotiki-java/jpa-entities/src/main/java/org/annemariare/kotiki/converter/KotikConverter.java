package org.annemariare.kotiki.converter;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.entity.KotikEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KotikConverter {
    @Autowired
    private OwnerConverter convert;

    public KotikDto entityToDto(KotikEntity entity) {
        return new KotikDto(entity.getId(), entity.getName(), entity.getBirthdayDate(), entity.getBreed(), entity.getColor() ,convert.entityToDto(entity.getOwner()));
    }

    public KotikEntity dtoToEntity(KotikDto dto) {
        return new KotikEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), dto.getBreed(), dto.getColor(), convert.dtoToEntity(dto.getOwner()));
    }
}
