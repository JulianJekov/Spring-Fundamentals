package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ModelAttribute("wordAddDTO")
    public WordAddDTO wordAddDTO() {
        return new WordAddDTO();
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("word-add");
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid WordAddDTO wordAddDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordAddDTO", wordAddDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.wordAddDTO", bindingResult);
            return new ModelAndView("redirect:/words/add");
        }

        boolean addedWord = wordService.addWord(wordAddDTO);

        if (!addedWord) {
            return new ModelAndView("redirect:/words/add");
        }

        return new ModelAndView("redirect:/home");
    }
}
