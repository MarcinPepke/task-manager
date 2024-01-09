package mpepke.taskmanager.controller.dto;

import lombok.Data;

import java.io.Serializable;


public record TaskDto(Long id, String tittle, String description, Boolean isFinished) implements Serializable {

}
