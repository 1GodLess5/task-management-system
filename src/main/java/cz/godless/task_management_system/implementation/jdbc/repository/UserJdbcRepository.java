package cz.godless.task_management_system.implementation.jdbc.repository;

import cz.godless.task_management_system.domain.User;
import cz.godless.task_management_system.implementation.jdbc.mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {
    private final UserRowMapper userRowMapper;
    private final JdbcTemplate jdbcTemplate;
    private static final String GET_ALL;

    static {
        GET_ALL = "select * from user";
    }

    public UserJdbcRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL, userRowMapper);
    }
}
