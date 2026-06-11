package m101_spring_test_basics.practice.task03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskStatus03 {
    static boolean canEdit(String status) {
        return "NEW".equals(status) || "IN_PROGRESS".equals(status);
    }
}
