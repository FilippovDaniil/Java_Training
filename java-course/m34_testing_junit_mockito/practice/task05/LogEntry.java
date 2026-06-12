package m34_testing_junit_mockito.practice.task05;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Классы под тестом (готовы)
class LogEntry {
    String user;
    String message;
    LogEntry(String user, String message) { this.user = user; this.message = message; }
}
