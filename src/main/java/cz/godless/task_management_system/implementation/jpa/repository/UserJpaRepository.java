package cz.godless.task_management_system.implementation.jpa.repository;

import cz.godless.task_management_system.implementation.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
