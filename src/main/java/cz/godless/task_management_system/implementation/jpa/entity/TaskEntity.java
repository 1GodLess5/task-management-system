package cz.godless.task_management_system.implementation.jpa.entity;

import cz.godless.task_management_system.domain.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity(name = "task")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @SequenceGenerator(name = "task_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @Setter
    private ProjectEntity project;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column
    @Setter
    private String description;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    public TaskEntity(UserEntity user, ProjectEntity project, String name, String description, TaskStatus status, OffsetDateTime createdAt) {
        this.user = user;
        this.project = project;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }
}
