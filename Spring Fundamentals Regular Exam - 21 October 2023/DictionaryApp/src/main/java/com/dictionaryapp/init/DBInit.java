package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    public DBInit(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (languageRepository.count() == 0) {
            List<Language> languages = Arrays.stream(LanguageName.values())
                    .map(languageName -> new Language().setLanguageName(languageName))
                    .toList();

            languageRepository.saveAll(languages);
        }
    }
}
