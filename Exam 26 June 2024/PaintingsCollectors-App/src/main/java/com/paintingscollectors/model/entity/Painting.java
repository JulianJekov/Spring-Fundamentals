package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "paintings")
public class Painting extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @ManyToOne(optional = false)
    private Style style;

    @ManyToOne(optional = false)
    private User owner;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isFavourite;

    @Column(nullable = false)
    private long votes;

    @ManyToMany(mappedBy = "ratedPaintings", cascade = CascadeType.REMOVE)
    private List<User> usersRated;

    public Painting() {
    }

    public String getName() {
        return name;
    }

    public Painting setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Painting setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Painting setStyle(Style style) {
        this.style = style;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Painting setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Painting setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public Painting setFavourite(Boolean favourite) {
        isFavourite = favourite;
        return this;
    }

    public Long getVotes() {
        return votes;
    }

    public Painting setVotes(Long votes) {
        this.votes = votes;
        return this;
    }

    public List<User> getUsersRated() {
        return usersRated;
    }

    public Painting setUsersRated(List<User> usersRated) {
        this.usersRated = usersRated;
        return this;
    }
}
