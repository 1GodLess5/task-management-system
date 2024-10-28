package cz.godless.task_management_system;

import cz.godless.task_management_system.api.request.UserAddRequest;
import cz.godless.task_management_system.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserIntegrationTests extends IntergrationTest {
    @Test
    public void getAll() {
        final ResponseEntity<List<User>> userResponse =  restTemplate.exchange(
                "/user",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        Assertions.assertNotNull(userResponse.getBody());
        Assertions.assertTrue(userResponse.getBody().size() >= 2);
    }

    @Test
    public void insert() {
        insertUser(generateRandomUser());
    }

    @Test
    public void getUser() {
        final UserAddRequest request = generateRandomUser();
        final long id = insertUser(request);

        final ResponseEntity<User> userResponse = restTemplate.getForEntity(
                "/user/" + id,
                    User.class
        );
        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        Assertions.assertNotNull(userResponse.getBody());

        final User user = userResponse.getBody();
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(request.getName(), user.getName());
        Assertions.assertEquals(request.getEmail(), user.getEmail());
    }

    private UserAddRequest generateRandomUser() {
        return new UserAddRequest(
                "name" + Math.random(),
                "email" + Math.random()
        );
    }

    private long insertUser(UserAddRequest request) {
        final ResponseEntity<Long> insertResponse = restTemplate.postForEntity(
                "/user",
                request,
                Long.class
        );

        Assertions.assertEquals(HttpStatus.CREATED, insertResponse.getStatusCode());
        Assertions.assertNotNull(insertResponse.getBody());

        return insertResponse.getBody();
    }
}
