package m34_testing_junit_mockito.practice.task01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Классы под тестом (готовы)
interface UserRepository {
    String findName(long id);
}
