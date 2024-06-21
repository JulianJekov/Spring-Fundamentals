package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.*;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;

    public WordServiceImpl(WordRepository wordRepository, ModelMapper modelMapper, CurrentUser currentUser, UserRepository userRepository, LanguageRepository languageRepository) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public boolean addWord(WordAddDTO wordAddDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(currentUser.getUsername());

        if (optionalUser.isEmpty()) {
            return false;
        }

        Optional<Language> optionalLanguage = languageRepository.findByLanguageName(wordAddDTO.getLanguage());

        if (optionalLanguage.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        Language language = optionalLanguage.get();

        Word word = modelMapper.map(wordAddDTO, Word.class);
        word.setLanguage(language);
        word.setAddedBy(user);
        wordRepository.save(word);

        return true;
    }

    @Override
    public HomeViewDTO homeView() {

        List<Word> allWords = wordRepository.findAll();

        List<GermanWordsDTO> german = getWords(allWords, LanguageName.GERMAN, GermanWordsDTO.class);
        List<SpanishWordsDTO> spanish = getWords(allWords, LanguageName.SPANISH, SpanishWordsDTO.class);
        List<ItalianWordsDTO> italian = getWords(allWords, LanguageName.ITALIAN, ItalianWordsDTO.class);
        List<FrenchWordsDTO> french = getWords(allWords, LanguageName.FRENCH, FrenchWordsDTO.class);


        return new HomeViewDTO(german, spanish, italian, french);

    }

    private <T> List<T> getWords(List<Word> allWords, LanguageName language, Class<T> clazz) {
        return allWords
                .stream()
                .filter(word -> word.getLanguage().getLanguageName().equals(language))
                .map(word -> modelMapper.map(word, clazz))
                .toList();
    }
}
