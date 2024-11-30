package cz.godless.task_management_system.implementation.jpa.repository;

import cz.godless.task_management_system.implementation.jpa.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAllByUserId(Long userId);
}
