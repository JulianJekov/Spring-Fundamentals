package com.paintingscollectors.model.dto.home;

public class AllOtherPaintingsDTO {
    private Long id;

    private String name;

    private String author;

    private String imageUrl;

    private String styleStyleName;

    private String ownerUsername;

    public AllOtherPaintingsDTO() {
    }

    public Long getId() {
        return id;
    }

    public AllOtherPaintingsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AllOtherPaintingsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public AllOtherPaintingsDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getStyleStyleName() {
        return styleStyleName;
    }

    public AllOtherPaintingsDTO setStyleStyleName(String styleStyleName) {
        this.styleStyleName = styleStyleName;
        return this;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public AllOtherPaintingsDTO setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AllOtherPaintingsDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
