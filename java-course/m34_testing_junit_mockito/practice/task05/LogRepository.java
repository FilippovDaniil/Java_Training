package m34_testing_junit_mockito.practice.task05;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

interface LogRepository {
    void save(LogEntry entry);
}
