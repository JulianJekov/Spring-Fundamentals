package com.dictionaryapp.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FrenchWordsDTO {
    private Long id;

    private String term;

    private String translation;

    private String example;

    private String addedByUsername;

    private LocalDate inputDate;
}
