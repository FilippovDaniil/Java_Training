package m34_testing_junit_mockito.practice.task07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Классы под тестом (готовы)
interface Inventory {
    boolean inStock(String product);
    void reduce(String product);
}
