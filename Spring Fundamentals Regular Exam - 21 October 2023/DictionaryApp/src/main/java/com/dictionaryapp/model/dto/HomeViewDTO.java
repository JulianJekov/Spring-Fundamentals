package com.dictionaryapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HomeViewDTO {

    private List<GermanWordsDTO> germanWords;
    private List<SpanishWordsDTO> spanishWords;
    private List<ItalianWordsDTO> italianWords;
    private List<FrenchWordsDTO> frenchWords;

    public HomeViewDTO(List<GermanWordsDTO> germanWords,
                       List<SpanishWordsDTO> spanishWords,
                       List<ItalianWordsDTO> italianWords,
                       List<FrenchWordsDTO> frenchWords) {
        this.germanWords = germanWords;
        this.spanishWords = spanishWords;
        this.italianWords = italianWords;
        this.frenchWords = frenchWords;
    }
}
