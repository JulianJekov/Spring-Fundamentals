package com.plannerapp.model.dto.task;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeDTO {
    private List<TaskViewDTO> assignedToMe;
    private List<TaskViewDTO> allAvailable;
    private int size;

    public TaskHomeDTO() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public TaskHomeDTO(List<TaskViewDTO> assignedToMe, List<TaskViewDTO> allAvailable) {
        this.assignedToMe = assignedToMe;
        this.allAvailable = allAvailable;
        this.size = allAvailable.size();
    }

    public List<TaskViewDTO> getAssignedToMe() {
        return assignedToMe;
    }

    public List<TaskViewDTO> getAllAvailable() {
        return allAvailable;
    }

    public int getSize() {
        return size;
    }
}
