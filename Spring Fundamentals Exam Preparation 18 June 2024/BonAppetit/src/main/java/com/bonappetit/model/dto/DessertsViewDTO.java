package com.bonappetit.model.dto;

public class DessertsViewDTO {

    private Long id;

    private String name;

    private String ingredients;

    public DessertsViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public DessertsViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DessertsViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public DessertsViewDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
