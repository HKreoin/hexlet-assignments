package exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.TaskMapper;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;
  
    @GetMapping("")
    public List<TaskDTO> index() {
        var tasks = taskRepository.findAll();
        var tasksDto = tasks.stream().map(taskMapper::map).toList();
        return tasksDto;
    }

    @GetMapping("{id}")
    public TaskDTO show(@PathVariable Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return taskMapper.map(task);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        task = taskRepository.save(task);
        var taskDto = taskMapper.map(task);
        return taskDto;
    }

    @PutMapping("{id}")
    public TaskDTO update(@Valid @RequestBody TaskUpdateDTO taskUpdateData, @PathVariable Long id) {
        var task = taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskMapper.update(taskUpdateData, task);
        var userId = taskUpdateData.getAssigneeId();
        var user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.addTask(task);
        task = taskRepository.save(task);
        var taskDto = taskMapper.map(task);
        return taskDto;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var task = taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        var userId = task.getAssignee().getId();
        var user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.removeTask(task);
        taskRepository.deleteById(id);
    }
    // END
}
