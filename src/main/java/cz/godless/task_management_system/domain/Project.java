package cz.godless.task_management_system.domain;

import lombok.Value;

@Value
public class Project {
    long id;
    long userId;
    String name;
    String description;
}
