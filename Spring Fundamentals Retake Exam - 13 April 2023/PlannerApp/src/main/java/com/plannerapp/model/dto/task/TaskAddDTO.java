package com.plannerapp.model.dto.task;

import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.vallidation.FutureDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddDTO {

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @NotBlank(message = "Due date is required")
    @FutureDate(message = "Due date must be in the future")
    private String dueDate;

    @NotNull(message = "You must select a priority!")
    private PriorityName priority;

    public String getDescription() {
        return description;
    }

    public TaskAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDueDate() {
        return dueDate;
    }

    public TaskAddDTO setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public TaskAddDTO setPriority(PriorityName priority) {
        this.priority = priority;
        return this;
    }
}
