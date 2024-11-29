package cz.godless.task_management_system.implementation.jdbc.service;

import cz.godless.task_management_system.api.ProjectService;
import cz.godless.task_management_system.api.TaskService;
import cz.godless.task_management_system.api.UserService;
import cz.godless.task_management_system.api.exception.BadRequestException;
import cz.godless.task_management_system.api.request.TaskAddRequest;
import cz.godless.task_management_system.api.request.TaskEditRequest;
import cz.godless.task_management_system.domain.Project;
import cz.godless.task_management_system.domain.Task;
import cz.godless.task_management_system.domain.TaskStatus;
import cz.godless.task_management_system.implementation.jdbc.repository.TaskJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceJdbcImpl implements TaskService {
    private final TaskJdbcRepository taskJdbcRepository;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskServiceJdbcImpl(TaskJdbcRepository taskJdbcRepository, UserService userService, ProjectService projectService) {
        this.taskJdbcRepository = taskJdbcRepository;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public long add(TaskAddRequest request) {
        return taskJdbcRepository.add(request);
    }

    @Override
    public void edit(long id, TaskEditRequest request) {
        if (this.get(id) != null) {
            taskJdbcRepository.update(id, request);
        }
    }

    @Override
    public void changeStatus(long id, TaskStatus status) {
        if (this.get(id) != null) {
            taskJdbcRepository.updateStatus(id, status);
        }
    }

    @Override
    public void assignProject(long taskId, long projectId) {
        final Task task = this.get(taskId);
        final Project project = projectService.get(projectId);

        if (task != null && project != null) {
            if (task.getUserId() != project.getUserId()) {
                throw new BadRequestException("Task and project must belong to the same user");
            }
            taskJdbcRepository.updateProject(taskId, projectId);
        }
    }

    @Override
    public void delete(long id) {
        if (this.get(id) != null) {
            taskJdbcRepository.delete(id);
        }
    }

    @Override
    public Task get(long id) {
        return taskJdbcRepository.getById(id);
    }

    @Override
    public List<Task> getAll() {
        return taskJdbcRepository.getAll();
    }

    @Override
    public List<Task> getAllByUserId(long userId) {
        if (userService.get(userId) != null) {
            return taskJdbcRepository.getAllByUser(userId);
        }
        return null;
    }

    @Override
    public List<Task> getAllByProjectId(long projectId) {
        if (projectService.get(projectId) != null) {
            return taskJdbcRepository.getAllByProject(projectId);
        }
        return null;
    }
}
