package cz.godless.task_management_system.api.request;

import cz.godless.task_management_system.domain.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskChangeStatusRequest {
    private TaskStatus status;
}
