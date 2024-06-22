package com.bonappetit.service;

import com.bonappetit.model.dto.HomeViewDTO;
import com.bonappetit.model.dto.RecipeAddDTO;

public interface RecipeService {
    boolean create(RecipeAddDTO recipeAddDTO);

    HomeViewDTO getAllRecipes();
}
