package cz.godless.task_management_system.implementation.jpa.service;

import cz.godless.task_management_system.api.ProjectService;
import cz.godless.task_management_system.api.UserService;
import cz.godless.task_management_system.api.exception.InternalErrorException;
import cz.godless.task_management_system.api.exception.ResourceNotFoundException;
import cz.godless.task_management_system.api.request.ProjectAddRequest;
import cz.godless.task_management_system.api.request.ProjectEditRequest;
import cz.godless.task_management_system.domain.Project;
import cz.godless.task_management_system.domain.User;
import cz.godless.task_management_system.implementation.jpa.entity.ProjectEntity;
import cz.godless.task_management_system.implementation.jpa.entity.UserEntity;
import cz.godless.task_management_system.implementation.jpa.repository.ProjectJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Profile("jpa")
public class ProjectServiceJpaImpl implements ProjectService {
    private final ProjectJpaRepository repository;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceJpaImpl.class);

    public ProjectServiceJpaImpl(ProjectJpaRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Project get(long id) {
        return repository
                .findById(id)
                .map(this::mapProjectEntityToProject)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " was not found."));
    }

    @Override
    public List<Project> getAll() {
        return repository.findAll().stream().map(this::mapProjectEntityToProject).toList();
    }

    @Override
    public List<Project> getAllByUser(long userId) {
        if (userService.get(userId) != null) {
            return repository.findAllByUserId(userId).stream().map(this::mapProjectEntityToProject).toList();
        }
        // cannot get here, would throw ResourceNotFoundException
        return null;
    }

    @Override
    public void delete(long id) {
        if (this.get(id) != null) {
            repository.deleteById(id);
        }
    }

    @Override
    public long add(ProjectAddRequest request) {
        final User user = userService.get(request.getUserId());
        final UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getEmail());
        try {
            return repository.save(
                    new ProjectEntity(
                            userEntity,
                            request.getName(),
                            request.getDescription(),
                            OffsetDateTime.now()
                    )).getId();
        } catch (DataAccessException e) {
            logger.error("Error while adding user", e);
            throw new InternalErrorException("Error while adding user.");
        }
    }

    @Override
    public void edit(long id, ProjectEditRequest request) {
        final ProjectEntity projectEntity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " was not found."));

        projectEntity.setName(request.getName());
        projectEntity.setDescription(request.getDescription());
        repository.save(projectEntity);
    }

    private Project mapProjectEntityToProject(ProjectEntity entity) {
        return new Project(
                entity.getId(),
                entity.getUser().getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt()
        );
    }
}
