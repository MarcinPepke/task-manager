package mpepke.taskmanager.controller.mapper;

import mpepke.taskmanager.controller.dto.TaskDto;
import mpepke.taskmanager.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDtoMapper {
    public static TaskDto mapTaskToTaskDto(Task task){
        return new TaskDto(task.getId(), task.getTittle(), task.getDescription(), task.getIsFinished());
    }
    public static List<TaskDto> mapTasksToTaskDtos(List<Task> tasks){
        return tasks.stream()
                .map(task -> new TaskDto(task.getId(), task.getTittle(), task.getDescription(), task.getIsFinished()))
                .collect(Collectors.toList());
    }
}
