package org.annemariare.kotiki.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.annemariare.kotiki.entity.KotikEntity;
import org.annemariare.kotiki.enums.Color;

import java.io.Serializable;
import java.util.Date;

import static org.annemariare.kotiki.converter.OwnerConverter.entityToDto;

public class KotikDto implements Serializable {
    private Long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdayDate;
    private String breed;
    private Color color;
    private OwnerDto owner;

    public KotikDto(KotikEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.birthdayDate = entity.getBirthdayDate();
        this.breed = entity.getBreed();
        this.color = entity.getColor();
        this.owner = entityToDto(entity.getOwner());
    }

    public KotikDto(Long id, String name, Date birthdayDate, String breed, Color color, OwnerDto owner) {
        this.id = id;
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
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

    public String getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }

    public OwnerDto getOwner() {
        return owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setOwner(OwnerDto owner) {
        this.owner = owner;
    }
}
