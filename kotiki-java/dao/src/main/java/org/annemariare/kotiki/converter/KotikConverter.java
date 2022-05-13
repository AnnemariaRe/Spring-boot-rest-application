package org.annemariare.kotiki.converter;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.entity.KotikEntity;

public class KotikConverter {
    public static KotikDto entityToDto(KotikEntity entity) {
        return new KotikDto(entity);
    }

    public static KotikEntity dtoToEntity(KotikDto dto) {
        return new KotikEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), dto.getBreed(), dto.getColor(), OwnerConverter.dtoToEntity(dto.getOwner()));
    }
}
