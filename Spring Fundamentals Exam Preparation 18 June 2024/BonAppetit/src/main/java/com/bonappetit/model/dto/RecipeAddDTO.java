package com.bonappetit.model.dto;

import com.bonappetit.model.enums.CategoryName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RecipeAddDTO {

    @Size(min = 2, max = 40, message = "Name length must be between 2 and 40 characters!")
    @NotNull
    private String name;

    @Size(min = 2, max = 80, message = "Ingredients length must be between 2 and 80 characters!")
    @NotNull
    private String ingredients;

    @NotNull(message = "You must select a category!")
    private CategoryName category;

    public RecipeAddDTO() {
    }

    public String getName() {
        return name;
    }

    public RecipeAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeAddDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public CategoryName getCategory() {
        return category;
    }

    public RecipeAddDTO setCategory(CategoryName category) {
        this.category = category;
        return this;
    }
}
