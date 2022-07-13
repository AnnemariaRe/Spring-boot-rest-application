package org.annemariare.cats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.annemariare.cats.enums.Color;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cats")
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdayDate;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "color", nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "first_cat_id")},
            inverseJoinColumns = {@JoinColumn(name = "second_cat_id")})
    private List<CatEntity> friends;

    public CatEntity() {}

    public CatEntity(Long id, String name, Date date, String breed, Color color, OwnerEntity owner) {
        this.id = id;
        this.name = name;
        this.birthdayDate = date;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }

    public void setFriend(CatEntity kotik) {
        if (getFriends() == null) {
            this.friends = new ArrayList<>();
            this.friends.add(kotik);
        } else {
            getFriends().add(kotik);
        }
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

    public OwnerEntity getOwner() {
        return owner;
    }

    public List<CatEntity> getFriends() {
        return friends;
    }
}
