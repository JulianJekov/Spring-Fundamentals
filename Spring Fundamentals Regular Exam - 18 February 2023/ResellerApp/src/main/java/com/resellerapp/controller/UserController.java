package com.resellerapp.controller;

import com.resellerapp.init.LoggedUser;
import com.resellerapp.model.dto.user.UserLoginDTO;
import com.resellerapp.model.dto.user.UserRegisterDTO;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final LoggedUser loggedUser;
    private final UserService userService;

    public UserController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
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
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return new ModelAndView("redirect:/register");
        }

        boolean isLogged = this.userService.register(userRegisterDTO);

        if (!isLogged) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("registrationErrors", true);
            return new ModelAndView("redirect:/register");
        }

        return new ModelAndView("login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return new ModelAndView("redirect:/login");
        }

        boolean isRegistered = this.userService.login(userLoginDTO);

        if (!isRegistered) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("loginErrors", true);
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        this.userService.logout();
        return new ModelAndView("redirect:/");
    }
}
