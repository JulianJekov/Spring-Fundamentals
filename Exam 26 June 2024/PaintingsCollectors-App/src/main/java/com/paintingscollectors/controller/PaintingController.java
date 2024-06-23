package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.painting.AddPaintingDTO;
import com.paintingscollectors.model.enums.StyleName;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PaintingController {

    private final CurrentUser currentUser;
    private final PaintingService paintingService;

    public PaintingController(CurrentUser currentUser, PaintingService paintingService) {
        this.currentUser = currentUser;
        this.paintingService = paintingService;
    }

    @ModelAttribute("addPaintingDTO")
    public AddPaintingDTO addPaintingDTO() {
        return new AddPaintingDTO();
    }

    @ModelAttribute("styles")
    public StyleName[] styleName() {
        return StyleName.values();
    }

    @GetMapping("/add")
    public ModelAndView add() {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("add-painting");
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@Valid AddPaintingDTO addPaintingDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPaintingDTO", addPaintingDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addPaintingDTO", bindingResult);
            return new ModelAndView("redirect:/add");
        }

        boolean isCreated = paintingService.createPainting(addPaintingDTO);

        if (!isCreated) {
            redirectAttributes.addFlashAttribute("addPaintingDTO", addPaintingDTO);
            redirectAttributes.addFlashAttribute("createError", true);
            return new ModelAndView("redirect:/add");
        }

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView doRemove(@PathVariable("id") Long id) {

        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        paintingService.remove(id);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/add/{id}")
    public ModelAndView doAddToFavourite(@PathVariable("id") Long id) {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        paintingService.addToFavourite(id);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/remove/favourite/{id}")
    public ModelAndView doRemoveFromFavourite(@PathVariable("id") Long id) {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        paintingService.removeFavourite(id);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/vote/{id}")
    public ModelAndView vote(@PathVariable("id") Long id) {
        paintingService.vote(id);
        return new ModelAndView("redirect:/home");
    }

}
