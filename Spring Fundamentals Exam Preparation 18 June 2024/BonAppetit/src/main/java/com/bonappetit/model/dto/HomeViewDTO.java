package com.bonappetit.model.dto;

import java.util.List;

public class HomeViewDTO {

    private List<DessertsViewDTO> desserts;
    private List<CocktailsViewDTO> cocktails;
    private List<MainDishesViewDTO> mainDishes;
    private List<FavouritesViewDTO> favourites;

    public HomeViewDTO() {}


    public HomeViewDTO(List<DessertsViewDTO> desserts,
                       List<CocktailsViewDTO> cocktails,
                       List<MainDishesViewDTO> mainDishes,
                       List<FavouritesViewDTO> favourites) {
        this.desserts = desserts;
        this.cocktails = cocktails;
        this.mainDishes = mainDishes;
        this.favourites = favourites;
    }

    public List<DessertsViewDTO> getDesserts() {
        return desserts;
    }

    public HomeViewDTO setDesserts(List<DessertsViewDTO> desserts) {
        this.desserts = desserts;
        return this;
    }

    public List<CocktailsViewDTO> getCocktails() {
        return cocktails;
    }

    public HomeViewDTO setCocktails(List<CocktailsViewDTO> cocktails) {
        this.cocktails = cocktails;
        return this;
    }

    public List<MainDishesViewDTO> getMainDishes() {
        return mainDishes;
    }

    public HomeViewDTO setMainDishes(List<MainDishesViewDTO> mainDishes) {
        this.mainDishes = mainDishes;
        return this;
    }

    public List<FavouritesViewDTO> getFavourites() {
        return favourites;
    }

    public HomeViewDTO setFavourites(List<FavouritesViewDTO> favourites) {
        this.favourites = favourites;
        return this;
    }
}
