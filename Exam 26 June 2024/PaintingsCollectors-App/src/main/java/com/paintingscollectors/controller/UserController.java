package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.user.UserLoginDTO;
import com.paintingscollectors.model.dto.user.UserRegisterDTO;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.CurrentUser;
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

    private final CurrentUser currentUser;
    private final UserService userService;

    public UserController(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();

    }@ModelAttribute("userLoginDTO")
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
            return new ModelAndView("redirect:/register");
        }

        boolean isRegistered = userService.register(userRegisterDTO);

        if (!isRegistered) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("registerError", true);
            return new ModelAndView("redirect:/register");
        }

        return new ModelAndView("redirect:/login");
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
        }
        boolean isLoggedIn = userService.login(userLoginDTO);

        if (!isLoggedIn) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("loginError", true);
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        if (!currentUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        userService.logout();
        return new ModelAndView("redirect:/");
    }
}
