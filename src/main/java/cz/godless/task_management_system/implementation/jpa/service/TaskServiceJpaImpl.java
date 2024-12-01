package cz.godless.task_management_system.implementation.jpa.service;

import cz.godless.task_management_system.api.ProjectService;
import cz.godless.task_management_system.api.TaskService;
import cz.godless.task_management_system.api.UserService;
import cz.godless.task_management_system.api.exception.BadRequestException;
import cz.godless.task_management_system.api.exception.InternalErrorException;
import cz.godless.task_management_system.api.exception.ResourceNotFoundException;
import cz.godless.task_management_system.api.request.TaskAddRequest;
import cz.godless.task_management_system.api.request.TaskEditRequest;
import cz.godless.task_management_system.domain.Project;
import cz.godless.task_management_system.domain.Task;
import cz.godless.task_management_system.domain.TaskStatus;
import cz.godless.task_management_system.domain.User;
import cz.godless.task_management_system.implementation.jpa.entity.ProjectEntity;
import cz.godless.task_management_system.implementation.jpa.entity.TaskEntity;
import cz.godless.task_management_system.implementation.jpa.entity.UserEntity;
import cz.godless.task_management_system.implementation.jpa.repository.TaskJpaRepository;
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
public class TaskServiceJpaImpl implements TaskService {
    private final TaskJpaRepository repository;
    private final ProjectService projectService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceJpaImpl.class);

    public TaskServiceJpaImpl(TaskJpaRepository repository, ProjectService projectService, UserService userService) {
        this.repository = repository;
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public long add(TaskAddRequest request) {
        final User user = userService.get(request.getUserId());
        final UserEntity userEntity = new UserEntity(user.getId(), user.getName(), user.getEmail());

        final ProjectEntity projectEntity;
        if (request.getProjectId() != null) {
            final Project project = projectService.get(request.getProjectId());
            projectEntity = new ProjectEntity(project.getId(), userEntity, project.getName(), project.getDescription(), project.getCreatedAt());
        } else {
            projectEntity = null;
        }

        try {
            return repository.save(new TaskEntity(userEntity, projectEntity, request.getName(), request.getDescription(), TaskStatus.NEW, OffsetDateTime.now())).getId();
        } catch (DataAccessException e) {
            logger.error("Error while adding task", e);
            throw new InternalErrorException("Error while adding task");
        }
    }

    @Override
    public void edit(long id, TaskEditRequest request) {
        final TaskEntity taskEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " was not found."));
        taskEntity.setName(request.getName());
        taskEntity.setDescription(request.getDescription());
        taskEntity.setStatus(request.getStatus());
        repository.save(taskEntity);
    }

    @Override
    public void changeStatus(long id, TaskStatus status) {
        final TaskEntity taskEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " was not found."));
        taskEntity.setStatus(status);
        repository.save(taskEntity);
    }

    @Override
    public void assignProject(long taskId, long projectId) {
        final TaskEntity taskEntity = repository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task with id: " + taskId + " was not found."));
        final Project project = projectService.get(projectId);

        if (!taskEntity.getUser().getId().equals(project.getUserId())) {
            throw new BadRequestException("Task and project must belong to the same user.");
        }

        final ProjectEntity projectEntity = new ProjectEntity(project.getId(), taskEntity.getUser(), project.getName(), project.getDescription(), project.getCreatedAt());
        taskEntity.setProject(projectEntity);
        repository.save(taskEntity);
    }

    @Override
    public void delete(long id) {
        if (this.get(id) != null) {
            repository.deleteById(id);
        }
    }

    @Override
    public Task get(long id) {
        return repository.findById(id)
                .map(this::mapTaskEntityToTask)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " was not found."));
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll().stream().map(this::mapTaskEntityToTask).toList();
    }

    @Override
    public List<Task> getAllByUserId(long userId) {
        if (userService.get(userId) != null) {
            return repository.findAllByUserId(userId).stream().map(this::mapTaskEntityToTask).toList();
        }

        return null;
    }

    @Override
    public List<Task> getAllByProjectId(long projectId) {
        if (projectService.get(projectId) != null) {
            return repository.findAllByProjectId(projectId).stream().map(this::mapTaskEntityToTask).toList();
        }

        return null;
    }

    private Task mapTaskEntityToTask(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId(),
                taskEntity.getUser().getId(),
                taskEntity.getProject() != null ? taskEntity.getProject().getId() : null,
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getStatus(),
                taskEntity.getCreatedAt()
        );
    }
}
