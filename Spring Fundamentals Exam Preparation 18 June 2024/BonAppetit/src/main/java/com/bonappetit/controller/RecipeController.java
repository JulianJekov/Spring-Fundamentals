package com.bonappetit.controller;

import com.bonappetit.model.dto.RecipeAddDTO;
import com.bonappetit.service.RecipeService;
import com.bonappetit.session.CurrentUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Component
@RequestMapping("recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final CurrentUser currentUser;

    public RecipeController(RecipeService recipeService, CurrentUser currentUser) {
        this.recipeService = recipeService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("recipeAddDTO")
    public RecipeAddDTO recipeAddDTO() {
        return new RecipeAddDTO();
    }


    @GetMapping("/add")
    public ModelAndView add() {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("recipe-add");
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@Valid RecipeAddDTO recipeAddDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddDTO",recipeAddDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.recipeAddDTO", bindingResult);
            return new ModelAndView("redirect:/recipes/add");
        }

        boolean isCreated = recipeService.create(recipeAddDTO);

        if (!isCreated) {
            redirectAttributes.addFlashAttribute("recipeAddDTO",recipeAddDTO);
            redirectAttributes.addFlashAttribute("createError", true);
            return new ModelAndView("redirect:/recipes/add");
        }

        return new ModelAndView("redirect:/home");
    }

}
