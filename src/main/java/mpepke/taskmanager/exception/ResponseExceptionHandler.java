package mpepke.taskmanager.exception;

import mpepke.taskmanager.service.exception.TaskDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(TaskDoesNotExistsException.class)
    public ResponseEntity<?> handleTeamDoesNotExistException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task does not exists");
    }

}
