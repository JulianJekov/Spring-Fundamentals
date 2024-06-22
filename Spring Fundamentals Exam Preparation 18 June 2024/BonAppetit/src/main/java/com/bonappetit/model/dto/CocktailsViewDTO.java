package com.bonappetit.model.dto;

public class CocktailsViewDTO {

    private Long id;

    private String name;

    private String ingredients;

    public CocktailsViewDTO() {}

    public Long getId() {
        return id;
    }

    public CocktailsViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CocktailsViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public CocktailsViewDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
