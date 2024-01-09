package mpepke.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String tittle;
    private String description;
    @Column(columnDefinition = "bool default false")
    private Boolean isFinished = false;



}
