package com.bonappetit.model.dto;

public class FavouritesViewDTO {

    private Long id;

    private String name;

    private String ingredients;

    public FavouritesViewDTO() {}

    public Long getId() {
        return id;
    }

    public FavouritesViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FavouritesViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public FavouritesViewDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
