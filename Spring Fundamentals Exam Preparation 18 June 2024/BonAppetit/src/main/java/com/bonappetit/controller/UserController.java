package com.bonappetit.controller;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.service.UserService;
import com.bonappetit.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public ModelAndView register() {
        if (currentUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (currentUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return new ModelAndView("redirect:/users/register");
        }

        boolean isRegistered = userService.register(userRegisterDTO);

        if (!isRegistered) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("registerError", true);
            return new ModelAndView("redirect:/users/register");
        }

        return new ModelAndView("redirect:/users/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        if (currentUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView doLogin(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (currentUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return new ModelAndView("redirect:/users/login");
        }
        boolean isLoggedIn = userService.login(userLoginDTO);

        if (!isLoggedIn) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("loginError", true);
            return new ModelAndView("redirect:/users/login");
        }

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/logout")
    public ModelAndView doLogout() {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        userService.logout();
        return new ModelAndView("redirect:/");
    }


    @PostMapping("/recipe/add-to-favourites/{id}")
    public ModelAndView addToFavourites(@PathVariable("id") Long id) {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        boolean isRecipeAdded = userService.addRecipeToFavourite(id);

        if (!isRecipeAdded) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("redirect:/home");
    }

}
