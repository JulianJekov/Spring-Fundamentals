package com.resellerapp.controller;

import com.resellerapp.init.LoggedUser;
import com.resellerapp.model.dto.offer.OfferAddDTO;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("offerAddDTO")
    public OfferAddDTO offerAddDTO() {
        return new OfferAddDTO();
    }


    @GetMapping("/offer/add")
    public ModelAndView add() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("offer-add");
    }

    @PostMapping("/offer/add")
    public ModelAndView add(@Valid OfferAddDTO offerAddDTO, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddDTO", offerAddDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.offerAddDTO", bindingResult);
            return new ModelAndView("redirect:/add/offer");
        }

        boolean isAdded = this.offerService.add(offerAddDTO);

        String view = isAdded ? "redirect:/home" : "redirect:/add/offer";

        return new ModelAndView(view);
    }

    @DeleteMapping("/offers/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }
        this.offerService.remove(id);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/offers/buy/{id}")
    public ModelAndView buy(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }
        this.offerService.buy(id);
        return new ModelAndView("redirect:/home");
    }
}
