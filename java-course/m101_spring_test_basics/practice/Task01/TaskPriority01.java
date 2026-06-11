package m101_spring_test_basics.practice.task01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskPriority01 {
    static String calc(boolean urgent, int daysLeft) {
        if (daysLeft < 0) throw new IllegalArgumentException("daysLeft < 0");
        if (urgent || daysLeft <= 2) return "HIGH";
        return "LOW";
    }
}
