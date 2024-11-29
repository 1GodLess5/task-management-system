package cz.godless.task_management_system.implementation.jpa.service;

import cz.godless.task_management_system.api.UserService;
import cz.godless.task_management_system.api.request.UserAddRequest;
import cz.godless.task_management_system.domain.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {
    @Override
    public long add(UserAddRequest request) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }
}
