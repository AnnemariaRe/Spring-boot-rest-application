package org.annemariare.kotiki.model;

import jakarta.persistence.*;

import org.annemariare.kotiki.enums.Color;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "kotiki")
public class Kotik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday_date", nullable = false)
    private Date birthdayDate;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "color", nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "first_kotik_id")},
            inverseJoinColumns = {@JoinColumn(name = "second_kotik_id")})
    private List<Kotik> friends;

    public int getId() {
        return id;
    }

    public void setId(int kotikId) {
        this.id = kotikId;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Kotik> getFriends() {
        return friends;
    }

    public void setFriends(List<Kotik> friends) {
        this.friends = friends;
    }
}
