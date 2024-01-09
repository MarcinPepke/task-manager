package mpepke.taskmanager.service;

import mpepke.taskmanager.entity.Task;
import mpepke.taskmanager.repository.TaskRepository;
import mpepke.taskmanager.service.exception.TaskDoesNotExistsException;
import mpepke.taskmanager.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    void setup() {
        task = new Task(1L, "testTitle", "testDesc", false);
    }

    @Test
    public void givenTaskObject_whenSaveTask_thenReturnTask() {

        when(taskRepository.save(any())).thenReturn(task);
        taskService.createTask(task);
        verify(taskRepository, times(1)).save(any());

    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTask() throws TaskDoesNotExistsException {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        taskService.deleteTask(task.getId());
        verify(taskRepository).delete(task);
    }
}
