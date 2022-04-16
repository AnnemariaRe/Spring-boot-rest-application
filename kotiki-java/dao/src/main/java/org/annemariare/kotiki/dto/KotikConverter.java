package org.annemariare.kotiki.dto;

import org.annemariare.kotiki.entity.KotikEntity;

public class KotikConverter {
    public static KotikDto entityToDto(KotikEntity entity) {
        KotikDto dto = new KotikDto(entity);
        return dto;
    }

    public static KotikEntity dtoToEntity(KotikDto dto) {
        KotikEntity entity = new KotikEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), dto.getBreed(), dto.getColor(), OwnerConverter.dtoToEntity(dto.getOwner()));
        return entity;
    }
}
