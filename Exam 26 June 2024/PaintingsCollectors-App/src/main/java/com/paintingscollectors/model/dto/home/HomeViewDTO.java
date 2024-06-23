package com.paintingscollectors.model.dto.home;

import java.util.List;

public class HomeViewDTO {

    List<MyPaintingsDTO> myPaintings;
    List<AllOtherPaintingsDTO> allOtherPaintings;
    List<MyFavouritePaintingsDTO> myFavouritePaintings;
    List<MostRatedDTO> mostRated;

    public HomeViewDTO() {
    }

    public HomeViewDTO(List<MyPaintingsDTO> myPaintings,
                       List<AllOtherPaintingsDTO> allOtherPaintings,
                       List<MyFavouritePaintingsDTO> myFavouritePaintings,
                       List<MostRatedDTO> mostRated) {
        this.myPaintings = myPaintings;
        this.allOtherPaintings = allOtherPaintings;
        this.myFavouritePaintings = myFavouritePaintings;
        this.mostRated = mostRated;
    }

    public List<MostRatedDTO> getMostRated() {
        return mostRated;
    }

    public HomeViewDTO setMostRated(List<MostRatedDTO> mostRated) {
        this.mostRated = mostRated;
        return this;
    }

    public List<MyPaintingsDTO> getMyPaintings() {
        return myPaintings;
    }

    public HomeViewDTO setMyPaintings(List<MyPaintingsDTO> myPaintings) {
        this.myPaintings = myPaintings;
        return this;
    }

    public List<AllOtherPaintingsDTO> getAllOtherPaintings() {
        return allOtherPaintings;
    }

    public HomeViewDTO setAllOtherPaintings(List<AllOtherPaintingsDTO> allOtherPaintings) {
        this.allOtherPaintings = allOtherPaintings;
        return this;
    }

    public List<MyFavouritePaintingsDTO> getMyFavouritePaintings() {
        return myFavouritePaintings;
    }

    public HomeViewDTO setMyFavouritePaintings(List<MyFavouritePaintingsDTO> myFavouritePaintings) {
        this.myFavouritePaintings = myFavouritePaintings;
        return this;
    }


}
