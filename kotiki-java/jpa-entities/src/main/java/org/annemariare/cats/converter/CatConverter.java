package org.annemariare.cats.converter;

import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.entity.CatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatConverter {
    @Autowired
    private OwnerConverter convert;

    public CatDto entityToDto(CatEntity entity) {
        return new CatDto(entity.getId(), entity.getName(), entity.getBirthdayDate(), entity.getBreed(), entity.getColor() ,convert.entityToDto(entity.getOwner()));
    }

    public CatEntity dtoToEntity(CatDto dto) {
        return new CatEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), dto.getBreed(), dto.getColor(), convert.dtoToEntity(dto.getOwner()));
    }
}
