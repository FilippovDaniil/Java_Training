package m15_layered_mvc_clean.practice.task04;

import java.util.ArrayList;
import java.util.List;

// Model: хранит задачи.
class TodoModel {
    private final List<String> tasks = new ArrayList<>();

    public void add(String task) {
        // TODO: добавить задачу
    }

    public List<String> items() {
        // TODO: вернуть список задач
        return tasks;
    }
}
