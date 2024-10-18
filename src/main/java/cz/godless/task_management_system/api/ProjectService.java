package cz.godless.task_management_system.api;

import cz.godless.task_management_system.api.request.ProjectAddRequest;
import cz.godless.task_management_system.api.request.ProjectEditRequest;
import cz.godless.task_management_system.domain.Project;

import java.util.List;

public interface ProjectService {
    Project get(long id);
    List<Project> getAll();
    List<Project> getAllByUser(long userId);
    void delete(long id);

    long add (ProjectAddRequest request);
    void edit(long id, ProjectEditRequest request);
}
