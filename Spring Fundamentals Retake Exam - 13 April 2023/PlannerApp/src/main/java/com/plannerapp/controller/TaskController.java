package com.plannerapp.controller;

import com.plannerapp.init.LoggedUser;
import com.plannerapp.model.dto.task.TaskAddDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.service.PriorityService;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    private final LoggedUser loggedUser;

    public TaskController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/tasks/add")
    public ModelAndView add(@ModelAttribute("taskAddDTO")TaskAddDTO taskAddDTO) {
        if (!this.loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("task-add");
    }

    @PostMapping("/tasks/add")
    public ModelAndView add(@ModelAttribute("taskAddDTO") @Valid TaskAddDTO taskAddDTO, BindingResult bindingResult) {
        if (!this.loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/task-add");
        }

        this.taskService.add(taskAddDTO);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/tasks/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!this.loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        this.taskService.remove(id);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/tasks/return/{id}")
    public ModelAndView returnTask(@PathVariable("id") Long id) {
        if (!this.loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        this.taskService.assign(id, null);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/tasks/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id) {
        if (!this.loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        this.taskService.assign(id, this.loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }
}
