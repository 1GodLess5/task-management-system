package cz.godless.task_management_system.implementation.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
