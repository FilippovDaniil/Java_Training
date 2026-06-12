package m25_architecture_testing_archunit.practice.task06;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Набор правил: прогоняет все и агрегирует нарушения.
class ArchTestSuite {
    private final List<ArchRule> rules = new ArrayList<>();

    public void add(ArchRule rule) {
        // TODO: добавить правило
    }

    public List<String> runAll(Map<String, Set<String>> deps) {
        // TODO: прогнать все правила, собрать все нарушения в один список
        return new ArrayList<>();
    }
}
