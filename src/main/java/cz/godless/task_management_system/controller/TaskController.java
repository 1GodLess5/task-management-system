package cz.godless.task_management_system.controller;

import cz.godless.task_management_system.api.TaskService;
import cz.godless.task_management_system.api.request.TaskAddRequest;
import cz.godless.task_management_system.api.request.TaskAssignStatusRequest;
import cz.godless.task_management_system.api.request.TaskChangeStatusRequest;
import cz.godless.task_management_system.api.request.TaskEditRequest;
import cz.godless.task_management_system.domain.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long projectId
    ) {
        if (userId != null) {
            return ResponseEntity.ok().body(taskService.getAllByUserId(userId));
        } else if (projectId != null) {
            return ResponseEntity.ok().body(taskService.getAllByProjectId(projectId));
        }
        return ResponseEntity.ok().body(taskService.getAll());
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody TaskAddRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.add(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> edit(@PathVariable("id") long id, @RequestBody TaskEditRequest request) {
        taskService.edit(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable("id") long id, @RequestBody TaskChangeStatusRequest request) {
        taskService.changeStatus(id, request.getStatus());
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/assign")
    public ResponseEntity<Void> assignProject(@PathVariable("id") long id, @RequestBody TaskAssignStatusRequest request) {
        taskService.assignProject(id, request.getProjectId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> get(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(taskService.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
