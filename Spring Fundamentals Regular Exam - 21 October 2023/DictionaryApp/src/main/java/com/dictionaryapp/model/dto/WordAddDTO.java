package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.enums.LanguageName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class WordAddDTO {

    @Size(min = 2, max = 40, message = "Term length must be between 2 and 40 characters!")
    @NotNull
    private String term;

    @Size(min = 2, max = 80, message = "Translation length must be between 2 and 80 characters!")
    @NotNull
    private String translation;

    @Size(min = 2, max = 200, message = "Example length must be between 2 and 200 characters!")
    private String example;

    @PastOrPresent(message = "The input date must be in the past or present!")
    @NotNull(message = "Date can not be empty")
    private LocalDate inputDate;

    @NotNull(message = "You must select a language!")
    private LanguageName language;
}
