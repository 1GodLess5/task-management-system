package cz.godless.task_management_system.domain;

import lombok.Value;

@Value
public class Project {
    long id;
    long userId;
    String name;
    String description;


    public Project(long id, long userId, String name, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }
}
