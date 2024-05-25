package com.plannerapp.service.impl;

import com.plannerapp.model.dto.task.TaskAddDTO;
import com.plannerapp.model.dto.task.TaskHomeDTO;
import com.plannerapp.model.dto.task.TaskViewDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.modelMapper = modelMapper;

        this.userRepository = userRepository;
    }

    @Override
    public void add(TaskAddDTO taskAddDTO) {
        Priority priority = this.priorityRepository.findByName(taskAddDTO.getPriority());

        Task task = this.modelMapper.map(taskAddDTO, Task.class).setPriority(priority);

        this.taskRepository.save(task);
    }

    @Override
    public void remove(Long id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public void assign(Long id, String username) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (username == null) {
                task.setAssignee(null);
            } else {
                User user = this.userRepository.findByUsername(username);
                task.setAssignee(user);
                task.setDueDate(LocalDate.now().plusDays(1));
            }
            this.taskRepository.save(task);
        }
    }

    @Override
    public TaskHomeDTO getHomeView(String username) {

        List<TaskViewDTO> allAssigned = getAllAssigned(username);
        List<TaskViewDTO> allAvailable = getAllAvailable();

        return new TaskHomeDTO(allAssigned, allAvailable);
    }

    private List<TaskViewDTO> getAllAvailable() {
        return this.taskRepository.findAll()
                .stream()
                .filter(t -> t.getAssignee() == null)
                .map(t -> this.modelMapper.map(t, TaskViewDTO.class))
                .toList();
    }

    private List<TaskViewDTO> getAllAssigned(String username) {
        User user = this.userRepository.findByUsername(username);
        return this.taskRepository.findByAssignee(user)
                .stream()
                .map(task -> this.modelMapper.map(task, TaskViewDTO.class))
                .collect(Collectors.toList());
    }
}
