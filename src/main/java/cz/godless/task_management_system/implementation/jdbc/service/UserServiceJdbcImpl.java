package cz.godless.task_management_system.implementation.jdbc.service;

import cz.godless.task_management_system.api.UserService;
import cz.godless.task_management_system.api.request.UserAddRequest;
import cz.godless.task_management_system.domain.User;
import cz.godless.task_management_system.implementation.jdbc.repository.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJdbcImpl implements UserService {
    private final UserJdbcRepository repository;

    public UserServiceJdbcImpl(UserJdbcRepository userJdbcRepository) {
        this.repository = userJdbcRepository;
    }

    @Override
    public long add(UserAddRequest request) {
        return repository.add(request);
    }

    @Override
    public void delete(long id) {

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
