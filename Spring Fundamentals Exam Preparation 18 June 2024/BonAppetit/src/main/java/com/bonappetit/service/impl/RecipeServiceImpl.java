package com.bonappetit.service.impl;

import com.bonappetit.model.dto.*;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.RecipeService;
import com.bonappetit.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper, CurrentUser currentUser, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean create(RecipeAddDTO recipeAddDTO) {
        Optional<Category> optionalCategory =
                categoryRepository.findByCategoryName(recipeAddDTO.getCategory());
        Optional<User> optionalUser = userRepository.findByUsername(currentUser.getUsername());

        if (optionalCategory.isEmpty() || optionalUser.isEmpty()) {
            return false;
        }

        Recipe recipe = modelMapper.map(recipeAddDTO, Recipe.class);
        recipe.setCategory(optionalCategory.get());
        recipe.setAddedBy(optionalUser.get());

        recipeRepository.save(recipe);
        return true;
    }

    @Override
    @Transactional
    public HomeViewDTO getAllRecipes() {
        List<Recipe> allRecipes = recipeRepository.findAll();

        List<DessertsViewDTO> desserts = getDessertsViewDTOS(allRecipes, CategoryName.DESSERT, DessertsViewDTO.class);
        List<CocktailsViewDTO> cocktails = getDessertsViewDTOS(allRecipes, CategoryName.COCKTAIL, CocktailsViewDTO.class);
        List<MainDishesViewDTO> mainDishes = getDessertsViewDTOS(allRecipes, CategoryName.MAIN_DISH, MainDishesViewDTO.class);

        Optional<User> optionalUser = userRepository.findByUsername(currentUser.getUsername());
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        List<FavouritesViewDTO> favourites = user.getFavouriteRecipes().stream().map(recipe -> modelMapper.map(recipe, FavouritesViewDTO.class))
                .toList();

        return new HomeViewDTO(desserts, cocktails, mainDishes, favourites);
    }

    private <T> List<T> getDessertsViewDTOS(List<Recipe> allRecipes, CategoryName category, Class<T> clazz) {
        return allRecipes.stream()
                .filter(recipe -> recipe.getCategory().getCategoryName().equals(category))
                .map(recipe -> modelMapper.map(recipe, clazz))
                .toList();

    }
}
