package m34_testing_junit_mockito.practice.task06;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Классы под тестом (готовы)
interface PriceRepository { double getPrice(long productId); }
