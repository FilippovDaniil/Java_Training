package m34_testing_junit_mockito.practice.task01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserService {
    private final UserRepository repo;

    UserService(UserRepository repo) {
        this.repo = repo;
    }

    String greet(long id) {
        return "Привет, " + repo.findName(id);
    }
}
