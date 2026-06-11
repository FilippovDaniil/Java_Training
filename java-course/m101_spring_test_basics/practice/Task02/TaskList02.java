package m101_spring_test_basics.practice.task02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskList02 {
    private final List<String> items = new ArrayList<>();
    void add(String t) { items.add(t); }
    void remove(String t) { items.remove(t); }
    int size() { return items.size(); }
}
