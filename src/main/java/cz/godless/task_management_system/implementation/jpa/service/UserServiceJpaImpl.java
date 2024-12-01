package cz.godless.task_management_system.implementation.jpa.service;

import cz.godless.task_management_system.api.UserService;
import cz.godless.task_management_system.api.exception.BadRequestException;
import cz.godless.task_management_system.api.exception.InternalErrorException;
import cz.godless.task_management_system.api.exception.ResourceNotFoundException;
import cz.godless.task_management_system.api.request.UserAddRequest;
import cz.godless.task_management_system.domain.User;
import cz.godless.task_management_system.implementation.jpa.entity.UserEntity;
import cz.godless.task_management_system.implementation.jpa.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jpa")
public class UserServiceJpaImpl implements UserService {
    private final UserJpaRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceJpaImpl.class);

    public UserServiceJpaImpl(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public long add(UserAddRequest request) {
        try {
            return repository.save(new UserEntity(request.getName(), request.getEmail())).getId();
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("User with email" + request.getEmail() + " already exists.");
        } catch (DataAccessException e) {
            logger.error("Error while adding user", e);
            throw new InternalErrorException("Error while adding user.");
        }
    }

    @Override
    public void delete(long id) {
        if (this.get(id) != null) {
            repository.deleteById(id);
        }
    }

    @Override
    public User get(long id) {
//        final Optional<UserEntity> userEntityOptional = repository.findById(id);
//        if (userEntityOptional.isPresent()) {
//            return mapUserEntityToUser(userEntityOptional.get());
//        } else {
//            throw new ResourceNotFoundException("User with id: " + id + " was not found.");
//        }
//        THE SAME CODE USING STREAM API
        return repository.findById(id)
                .map(this::mapUserEntityToUser)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " was not found."));

    }

    @Override
    public List<User> getAll() {
        return repository.findAll().stream().map(this::mapUserEntityToUser).toList();
    }

    private User mapUserEntityToUser(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }
}
