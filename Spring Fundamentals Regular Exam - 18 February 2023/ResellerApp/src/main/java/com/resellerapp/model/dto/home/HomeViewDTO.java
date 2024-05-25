package com.resellerapp.model.dto.home;

import java.util.List;

public class HomeViewDTO {

    private List<MyOffersDTO> myOffers;
    private List<BoughtOffersDTO> boughtOffers;
    private List<AllOtherOffersDTO> allOtherOffers;
    private long totalOtherOffers;

    public HomeViewDTO(List<MyOffersDTO> myOffers) {
        this.myOffers = myOffers;
    }

    public HomeViewDTO(List<MyOffersDTO> myOffers, List<BoughtOffersDTO> boughtOffers, List<AllOtherOffersDTO> allOtherOffers) {
        this.myOffers = myOffers;
        this.boughtOffers = boughtOffers;
        this.allOtherOffers = allOtherOffers;
        this.totalOtherOffers = allOtherOffers.size();
    }

    public List<MyOffersDTO> getMyOffers() {
        return myOffers;
    }

    public HomeViewDTO setMyOffers(List<MyOffersDTO> myOffers) {
        this.myOffers = myOffers;
        return this;
    }

    public List<BoughtOffersDTO> getBoughtOffers() {
        return boughtOffers;
    }

    public HomeViewDTO setBoughtOffers(List<BoughtOffersDTO> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }

    public List<AllOtherOffersDTO> getAllOtherOffers() {
        return allOtherOffers;
    }

    public HomeViewDTO setAllOtherOffers(List<AllOtherOffersDTO> allOtherOffers) {
        this.allOtherOffers = allOtherOffers;
        return this;
    }

    public Long getTotalOtherOffers() {
        return totalOtherOffers;
    }

    public HomeViewDTO setTotalOtherOffers(Long totalOtherOffers) {
        this.totalOtherOffers = totalOtherOffers;
        return this;
    }
}
