package com.plannerapp.model.dto.task;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.enums.PriorityName;

public class TaskViewDTO {

    private Long id;

    private String description;

    private String dueDate;

    private Priority priority;



    public String getDescription() {
        return description;
    }

    public TaskViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDueDate() {
        return dueDate;
    }

    public TaskViewDTO setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskViewDTO setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TaskViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public static TaskViewDTO createFromTask(Task task) {
        TaskViewDTO taskViewDTO = new TaskViewDTO();

        taskViewDTO.setId(task.getId());
        taskViewDTO.setDescription(task.getDescription());
        taskViewDTO.setPriority(task.getPriority());
        taskViewDTO.setDueDate(task.getDueDate().toString());

        return taskViewDTO;
    }
}
