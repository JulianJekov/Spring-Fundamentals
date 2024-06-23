package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.home.HomeViewDTO;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final PaintingService paintingService;

    public HomeController(CurrentUser currentUser, PaintingService paintingService) {
        this.currentUser = currentUser;
        this.paintingService = paintingService;
    }

    @ModelAttribute("allPaintings")
    public HomeViewDTO homeViewDTO(){
        return new HomeViewDTO();
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

        modelAndView.addObject("allPaintings", paintingService.getAllPaintings());

        return modelAndView;
    }

}
