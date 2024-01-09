package mpepke.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import mpepke.taskmanager.controller.dto.TaskDto;
import mpepke.taskmanager.controller.request.TaskRequest;
import mpepke.taskmanager.controller.request.TaskUpdateRequest;
import mpepke.taskmanager.entity.Task;
import mpepke.taskmanager.repository.TaskRepository;
import mpepke.taskmanager.service.TaskService;
import mpepke.taskmanager.service.exception.TaskDoesNotExistsException;
import org.springframework.stereotype.Service;

import java.util.List;


import static mpepke.taskmanager.controller.mapper.TaskDtoMapper.mapTaskToTaskDto;
import static mpepke.taskmanager.controller.mapper.TaskDtoMapper.mapTasksToTaskDtos;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Override
    public TaskDto createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return mapTaskToTaskDto(savedTask);
    }

    @Override
    public TaskDto updateTask(TaskUpdateRequest request) throws TaskDoesNotExistsException {
        Task taskToUpdate = taskRepository.findById(request.getTaskId()).orElseThrow(TaskDoesNotExistsException::new);
        taskToUpdate.setDescription(request.getDescription());
        taskToUpdate.setTittle(request.getTitle());
        return mapTaskToTaskDto(taskRepository.save(taskToUpdate));

    }

    @Override
    public void deleteTask(Long taskId) throws TaskDoesNotExistsException {
        Task taskToDelete = taskRepository.findById(taskId).orElseThrow(TaskDoesNotExistsException::new);
        taskRepository.delete(taskToDelete);


    }

    @Override
    public void setTaskAsFinished(Long taskId) throws TaskDoesNotExistsException {
        Task taskToSetAsFinished = taskRepository.findById(taskId).orElseThrow(TaskDoesNotExistsException::new);
        taskToSetAsFinished.setIsFinished(true);
        taskRepository.save(taskToSetAsFinished);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();
        return mapTasksToTaskDtos(allTasks);
    }

    @Override
    public Task findTaskById(Long taskId) throws TaskDoesNotExistsException {
        return taskRepository.findById(taskId).orElseThrow(TaskDoesNotExistsException::new);
    }

    @Override
    public void setTaskAsUnfinished(Long taskId) throws TaskDoesNotExistsException {
        Task taskToSetAsFinished = taskRepository.findById(taskId).orElseThrow(TaskDoesNotExistsException::new);
        taskToSetAsFinished.setIsFinished(false);
        taskRepository.save(taskToSetAsFinished);
    }
}
