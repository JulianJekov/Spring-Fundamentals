package com.bonappetit.model.dto;

public class MainDishesViewDTO {

    private Long id;

    private String name;

    private String ingredients;

    public MainDishesViewDTO() {}

    public Long getId() {
        return id;
    }

    public MainDishesViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MainDishesViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public MainDishesViewDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
