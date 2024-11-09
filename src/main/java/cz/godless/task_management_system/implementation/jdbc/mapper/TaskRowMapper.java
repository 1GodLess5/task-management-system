package cz.godless.task_management_system.implementation.jdbc.mapper;

import cz.godless.task_management_system.domain.Task;
import cz.godless.task_management_system.domain.TaskStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;

@Component
public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task (
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getObject("project_id") != null ? rs.getLong("project_id") : null,
                rs.getString("name"),
                rs.getString("description"),
                TaskStatus.fromString(rs.getString("status")),
                rs.getTimestamp("created_at").toLocalDateTime().atOffset(ZoneOffset.UTC)
        );
    }
}
