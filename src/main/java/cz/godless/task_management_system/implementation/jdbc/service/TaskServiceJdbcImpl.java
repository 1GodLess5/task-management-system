package cz.godless.task_management_system.implementation.jdbc.service;

import cz.godless.task_management_system.api.TaskService;
import cz.godless.task_management_system.api.request.TaskAddRequest;
import cz.godless.task_management_system.api.request.TaskEditRequest;
import cz.godless.task_management_system.domain.Task;
import cz.godless.task_management_system.domain.TaskStatus;

import java.util.List;

public class TaskServiceJdbcImpl implements TaskService {
    @Override
    public long add(TaskAddRequest request) {
        return 0;
    }

    @Override
    public void edit(long id, TaskEditRequest request) {

    }

    @Override
    public void changeStatus(long id, TaskStatus status) {

    }

    @Override
    public void assignProject(long taskId, long projectId) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Task get(long id) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        return List.of();
    }

    @Override
    public List<Task> getAllByUserId(long userId) {
        return List.of();
    }

    @Override
    public List<Task> getAllByProjectId(long projectId) {
        return List.of();
    }
}
