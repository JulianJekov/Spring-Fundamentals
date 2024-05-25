package com.resellerapp.service;

import com.resellerapp.model.dto.home.HomeViewDTO;
import com.resellerapp.model.dto.offer.OfferAddDTO;

public interface OfferService {
    boolean add(OfferAddDTO offerAddDTO);

    HomeViewDTO getHomePageOffersView();

    void remove(Long id);

    void buy(Long id);
}
