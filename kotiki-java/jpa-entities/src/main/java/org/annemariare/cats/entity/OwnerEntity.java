package org.annemariare.cats.entity;

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
    private List<CatEntity> cats;

    @OneToOne(mappedBy = "owner")
    private UserEntity user;

    public OwnerEntity() {}

    public OwnerEntity(Long id, String name, Date date, List<CatEntity> cats) {
        this.id = id;
        this.name = name;
        this.birthdayDate = date;
        this.cats = cats;
    }

    public void setCat(CatEntity cat) {
        if (this.cats.isEmpty()) {
            this.cats = new ArrayList<>();
            this.cats.add(cat);
        } else {
            this.cats.add(cat);
        }
    }

    public void setCats(List<CatEntity> cats) {
        if (this.cats.isEmpty()) {
            this.cats = cats;
        } else {
            this.cats.addAll(cats);
        }
    }

    public String getName() {
        return name;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public List<CatEntity> getCats() {
        return cats;
    }

    public UserEntity getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }
}
