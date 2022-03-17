package ru.itmo.kotiki.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "owner_id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday_date", nullable = false)
    private Date birthdayDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kotik> kotiki;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Kotik> getKotiki() {
        return kotiki;
    }

    public void setKotiki(List<Kotik> kotiki) {
        this.kotiki = kotiki;
    }
}
