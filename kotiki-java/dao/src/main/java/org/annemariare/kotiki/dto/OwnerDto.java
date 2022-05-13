package org.annemariare.kotiki.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.annemariare.kotiki.entity.OwnerEntity;

import java.io.Serializable;
import java.util.Date;

public class OwnerDto implements Serializable {
    private Long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdayDate;

    public OwnerDto(OwnerEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.birthdayDate = entity.getBirthdayDate();
    }

    public OwnerDto(Long id, String name, Date birthdayDate) {
        this.id = id;
        this.name = name;
        this.birthdayDate = birthdayDate;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

}
