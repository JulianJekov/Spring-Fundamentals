package com.plannerapp.service;

import com.plannerapp.model.dto.task.TaskAddDTO;
import com.plannerapp.model.dto.task.TaskHomeDTO;

public interface TaskService {

    void add(TaskAddDTO taskAddDTO);

    void remove(Long id);

    void assign(Long id, String username);

    TaskHomeDTO getHomeView(String username);

}
