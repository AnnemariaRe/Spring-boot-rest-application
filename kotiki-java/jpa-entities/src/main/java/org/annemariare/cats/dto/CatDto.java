package org.annemariare.cats.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.annemariare.cats.enums.Color;

import java.io.Serializable;
import java.util.Date;

public class CatDto implements Serializable {
    private final Long id;
    private final String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private final Date birthdayDate;
    private final String breed;
    private final Color color;
    private final OwnerDto owner;

    @JsonCreator
    public CatDto(@JsonProperty("id") Long id,
                  @JsonProperty("name") String name,
                  @JsonProperty("birthdayDate") Date birthdayDate,
                  @JsonProperty("breed") String breed,
                  @JsonProperty("color") Color color,
                  @JsonProperty("owner") OwnerDto owner) {
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
}
