package m34_testing_junit_mockito.practice.task02;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

// Классы под тестом (готовы)
interface UserRepo { void save(String email); }
