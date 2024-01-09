package mpepke.taskmanager.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import mpepke.taskmanager.controller.dto.TaskDto;
import mpepke.taskmanager.controller.request.TaskRequest;
import mpepke.taskmanager.controller.request.TaskUpdateRequest;
import mpepke.taskmanager.entity.Task;
import mpepke.taskmanager.service.exception.TaskDoesNotExistsException;
import mpepke.taskmanager.service.impl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/task")
public class TaskController {


    private final TaskServiceImpl taskService;

    @GetMapping(path = "/all")
    private ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> allTasks = taskService.getAllTasks();
        return ResponseEntity
                .ok()
                .body(allTasks);
    }

    @PostMapping(path = "/add")
    private ResponseEntity<TaskDto> addTask(@RequestBody TaskRequest request) {
        Task newTask = new Task();
        newTask.setTittle(request.getTitle());
        newTask.setDescription(request.getDescription());
        TaskDto createdTask = taskService.createTask(newTask);
        return ResponseEntity
                .ok()
                .body(createdTask);
    }

    @PutMapping(path = "/update")
    private ResponseEntity<TaskDto> updateTaskInfo(@RequestBody TaskUpdateRequest taskRequest) throws TaskDoesNotExistsException {
        TaskDto updatedTask = taskService.updateTask(taskRequest);
        return ResponseEntity.ok().body(updatedTask);

    }

    @DeleteMapping(path = "/delete/{taskId}")
    private ResponseEntity<?> deleteTask(@PathVariable String taskId) throws TaskDoesNotExistsException {
        taskService.deleteTask(Long.valueOf(taskId));
        return ResponseEntity.status(HttpStatus.OK).body("Deleted task");
    }

    @PutMapping("/finish/{taskId}")
    private ResponseEntity<?> setTaskAsFinished(@PathVariable String taskId) throws TaskDoesNotExistsException {
        taskService.setTaskAsFinished(Long.valueOf(taskId));
        return ResponseEntity.ok().body("Set as finished");
    }

    @PutMapping("/un-finish/{taskId}")
    private ResponseEntity<?> setTaskAsUnfinished(@PathVariable String taskId) throws TaskDoesNotExistsException {
        taskService.setTaskAsUnfinished(Long.valueOf(taskId));
        return ResponseEntity.ok().body("Set as unfinished");
    }


}
