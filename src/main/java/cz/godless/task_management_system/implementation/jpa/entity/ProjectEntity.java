package cz.godless.task_management_system.implementation.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity(name = "project")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    @Id
    @SequenceGenerator(name = "project_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    private Long id;

    // MANY equals to Project, ONE equals to User
    // Many Projects can be assigned to one User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    @Setter
    private String name;

    @Setter
    private String description;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    public ProjectEntity(UserEntity user, String name, String description, OffsetDateTime createdAt) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
}
