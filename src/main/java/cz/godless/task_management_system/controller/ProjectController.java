package cz.godless.task_management_system.controller;

import cz.godless.task_management_system.api.ProjectService;
import cz.godless.task_management_system.api.request.ProjectAddRequest;
import cz.godless.task_management_system.domain.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok().body(projectService.getAllByUser(userId));
        }
        return ResponseEntity.ok().body(projectService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(projectService.get(id));
    }


    @PostMapping
    public ResponseEntity<Long> add(@RequestBody ProjectAddRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.add(request));
    }
}
