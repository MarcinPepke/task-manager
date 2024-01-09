package mpepke.taskmanager.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskUpdateRequest {
    private final Long taskId;
    private final String title;
    private final String description;
}
