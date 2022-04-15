package org.annemariare.kotiki.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "owner_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdayDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KotikEntity> kotiki;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public List<KotikEntity> getKotiki() {
        return kotiki;
    }
}
