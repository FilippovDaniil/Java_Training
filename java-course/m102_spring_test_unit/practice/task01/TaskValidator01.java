package m102_spring_test_unit.practice.task01;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskValidator01 {
    static void validate(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Заголовок пустой");
        if (title.length() > 255)
            throw new IllegalArgumentException("Заголовок слишком длинный");
    }
}
