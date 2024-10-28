package cz.godless.task_management_system.implementation.jdbc.mapper;

import cz.godless.task_management_system.domain.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;

public class ProjectRowMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Project (
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime().atOffset(ZoneOffset.UTC)
        );
    }
}
