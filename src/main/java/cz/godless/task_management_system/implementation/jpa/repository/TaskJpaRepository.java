package cz.godless.task_management_system.implementation.jpa.repository;

import cz.godless.task_management_system.implementation.jpa.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findAllByUserId(Long userId);
    List<TaskEntity> findAllByProjectId(Long projectId);
}
