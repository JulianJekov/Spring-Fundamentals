package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.home.HomeViewDTO;
import com.paintingscollectors.model.dto.painting.AddPaintingDTO;

public interface PaintingService {
    boolean createPainting(AddPaintingDTO addPaintingDTO);

    HomeViewDTO getAllPaintings();

    void remove(Long id);

    void addToFavourite(Long id);

    void removeFavourite(Long id);

    void vote(Long id);
}
