package cz.godless.task_management_system.implementation.jdbc.service;

import cz.godless.task_management_system.api.UserService;
import cz.godless.task_management_system.api.request.UserAddRequest;
import cz.godless.task_management_system.domain.User;
import cz.godless.task_management_system.implementation.jdbc.repository.ProjectJdbcRepository;
import cz.godless.task_management_system.implementation.jdbc.repository.TaskJdbcRepository;
import cz.godless.task_management_system.implementation.jdbc.repository.UserJdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jdbc")
public class UserServiceJdbcImpl implements UserService {
    private final UserJdbcRepository repository;
    private final ProjectJdbcRepository projectJdbcRepository;
    private final TaskJdbcRepository taskJdbcRepository;

    public UserServiceJdbcImpl(UserJdbcRepository userJdbcRepository, ProjectJdbcRepository projectJdbcRepository, TaskJdbcRepository taskJdbcRepository) {
        this.repository = userJdbcRepository;
        this.projectJdbcRepository = projectJdbcRepository;
        this.taskJdbcRepository = taskJdbcRepository;
    }

    @Override
    public long add(UserAddRequest request) {
        return repository.add(request);
    }

    @Override
    public void delete(long id) {
        if (this.get(id) != null) {
            taskJdbcRepository.deleteAllByUser(id);
            projectJdbcRepository.deleteAllByUser(id);
            repository.delete(id);
        }
    }

    @Override
    public User get(long id) {
        return repository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
