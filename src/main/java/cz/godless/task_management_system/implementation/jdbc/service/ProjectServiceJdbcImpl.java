package cz.godless.task_management_system.implementation.jdbc.service;

import cz.godless.task_management_system.api.ProjectService;
import cz.godless.task_management_system.api.request.ProjectAddRequest;
import cz.godless.task_management_system.api.request.ProjectEditRequest;
import cz.godless.task_management_system.domain.Project;
import cz.godless.task_management_system.implementation.jdbc.repository.ProjectJdbcRepository;
import cz.godless.task_management_system.implementation.jdbc.repository.TaskJdbcRepository;
import cz.godless.task_management_system.implementation.jdbc.repository.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceJdbcImpl implements ProjectService {
    private final ProjectJdbcRepository projectJdbcRepository;
    private final UserJdbcRepository userJdbcRepository;
    private final TaskJdbcRepository taskJdbcRepository;

    public ProjectServiceJdbcImpl(ProjectJdbcRepository projectJdbcRepository, UserJdbcRepository userJdbcRepository, TaskJdbcRepository taskJdbcRepository) {
        this.projectJdbcRepository = projectJdbcRepository;
        this.userJdbcRepository = userJdbcRepository;
        this.taskJdbcRepository = taskJdbcRepository;
    }

    @Override
    public Project get(long id) {
        return projectJdbcRepository.getById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectJdbcRepository.getAll();
    }

    @Override
    public List<Project> getAllByUser(long userId) {
        if (userJdbcRepository.getById(userId) != null) {
            return projectJdbcRepository.getAllByUserId(userId);
        }

        // this can not happen, because if condition above returns null, it will throw exception anyway
        return null;
    }

    @Override
    public void delete(long id) {
        if (this.get(id) != null) {
            taskJdbcRepository.deleteAllByProject(id);
            projectJdbcRepository.delete(id);
        }
    }

    @Override
    public long add(ProjectAddRequest request) {
        return projectJdbcRepository.add(request);
    }

    @Override
    public void edit(long id, ProjectEditRequest request) {
        if (this.get(id) != null) {
            projectJdbcRepository.update(id, request);
        }
    }
}
