package cz.godless.task_management_system.domain;

import lombok.Value;

@Value
public class User {
    long id;
    String name;
    String email;
}
