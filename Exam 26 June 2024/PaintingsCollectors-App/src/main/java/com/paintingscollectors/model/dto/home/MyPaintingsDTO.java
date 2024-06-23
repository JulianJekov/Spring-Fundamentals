package com.paintingscollectors.model.dto.home;

public class MyPaintingsDTO {

    private Long id;

    private String name;

    private String author;

    private String styleStyleName;

    private String imageUrl;

    public MyPaintingsDTO() {
    }

    public Long getId() {
        return id;
    }

    public MyPaintingsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MyPaintingsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MyPaintingsDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getStyleStyleName() {
        return styleStyleName;
    }

    public MyPaintingsDTO setStyleStyleName(String styleStyleName) {
        this.styleStyleName = styleStyleName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MyPaintingsDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
