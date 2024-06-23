package com.paintingscollectors.model.dto.home;

public class MyFavouritePaintingsDTO {

    private Long id;

    private String name;

    private String author;

    private String imageUrl;

    private String ownerUsername;

    public MyFavouritePaintingsDTO() {
    }

    public Long getId() {
        return id;
    }

    public MyFavouritePaintingsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MyFavouritePaintingsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MyFavouritePaintingsDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MyFavouritePaintingsDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public MyFavouritePaintingsDTO setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
        return this;
    }
}
