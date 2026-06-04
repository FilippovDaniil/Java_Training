import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

// Класс под тестом (готов)
class MathUtils {
    static boolean isEven(int n) { return n % 2 == 0; }
    static int add(int a, int b) { return a + b; }
}
