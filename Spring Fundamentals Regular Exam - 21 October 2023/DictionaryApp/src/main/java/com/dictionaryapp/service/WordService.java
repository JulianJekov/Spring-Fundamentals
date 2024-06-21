package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.HomeViewDTO;
import com.dictionaryapp.model.dto.WordAddDTO;

import java.util.List;

public interface WordService {
    boolean addWord(WordAddDTO wordAddDTO);

    HomeViewDTO homeView();
}
