package mpepke.taskmanager.service;

import mpepke.taskmanager.controller.dto.TaskDto;
import mpepke.taskmanager.controller.request.TaskUpdateRequest;
import mpepke.taskmanager.entity.Task;
import mpepke.taskmanager.service.exception.TaskDoesNotExistsException;

import java.util.List;

public interface TaskService {

    TaskDto createTask(Task task);

    TaskDto updateTask(TaskUpdateRequest request) throws TaskDoesNotExistsException;

    void deleteTask(Long taskId) throws TaskDoesNotExistsException;

    void setTaskAsFinished(Long taskId) throws TaskDoesNotExistsException;

    List<TaskDto> getAllTasks();

    Task findTaskById(Long taskId) throws TaskDoesNotExistsException;

    void setTaskAsUnfinished(Long valueOf) throws TaskDoesNotExistsException;
}
