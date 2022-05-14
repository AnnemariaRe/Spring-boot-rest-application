package org.annemariare.kotiki.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerEntity {
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdayDate;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<KotikEntity> kotiki;

    @OneToOne(mappedBy = "owner")
    private UserEntity user;

    public OwnerEntity() {}

    public OwnerEntity(Long id, String name, Date date, List<KotikEntity> kotiki) {
        this.id = id;
        this.name = name;
        this.birthdayDate = date;
        this.kotiki = kotiki;
    }

    public void setKotik(KotikEntity kotik) {
        if (this.kotiki.isEmpty()) {
            this.kotiki = new ArrayList<>();
            this.kotiki.add(kotik);
        } else {
            this.kotiki.add(kotik);
        }
    }

    public void setKotiki(List<KotikEntity> kotiki) {
        if (this.kotiki.isEmpty()) {
            this.kotiki = kotiki;
        } else {
            this.kotiki.addAll(kotiki);
        }
    }

    public String getName() {
        return name;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public List<KotikEntity> getKotiki() {
        return kotiki;
    }

    public UserEntity getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }
}
