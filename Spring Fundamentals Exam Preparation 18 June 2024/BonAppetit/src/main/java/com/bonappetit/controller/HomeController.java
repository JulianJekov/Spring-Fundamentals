package com.bonappetit.controller;

import com.bonappetit.service.RecipeService;
import com.bonappetit.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final RecipeService recipeService;
    private final CurrentUser currentUser;

    public HomeController(RecipeService recipeService, CurrentUser currentUser) {
        this.recipeService = recipeService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (currentUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {

        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("allRecipes", recipeService.getAllRecipes());

        return modelAndView;
    }

}
