package mpepke.taskmanager.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequest {
    private final String title;
    private final String description;
}
