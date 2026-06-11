package m34_testing_junit_mockito.practice.task04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Классы под тестом (готовы)
interface RemoteApi { String fetch(); }
