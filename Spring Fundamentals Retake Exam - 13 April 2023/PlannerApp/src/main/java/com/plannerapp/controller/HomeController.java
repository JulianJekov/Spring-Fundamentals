package com.plannerapp.controller;

import com.plannerapp.init.LoggedUser;
import com.plannerapp.model.dto.task.TaskHomeDTO;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final TaskService taskService;

    public HomeController(LoggedUser loggedUser, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (this.loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("index");
    }
    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        TaskHomeDTO viewModel = taskService.getHomeView(loggedUser.getUsername());

        return new ModelAndView("home", "tasks", viewModel);
    }

}
