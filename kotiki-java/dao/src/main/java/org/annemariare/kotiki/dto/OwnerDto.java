package org.annemariare.kotiki.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.annemariare.kotiki.entity.OwnerEntity;

import java.util.ArrayList;
import java.util.Date;

public class OwnerDto {
    private Long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdayDate;

    public OwnerDto(OwnerEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.birthdayDate = entity.getBirthdayDate();
    }

    public OwnerDto() {}

    public static OwnerDto entityToDto(OwnerEntity entity) {
        OwnerDto dto = new OwnerDto(entity);
        return dto;
    }

    public static OwnerEntity dtoToEntity(OwnerDto dto) {
        OwnerEntity entity = new OwnerEntity(dto.getId(), dto.getName(), dto.getBirthdayDate(), new ArrayList<>());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }
}
