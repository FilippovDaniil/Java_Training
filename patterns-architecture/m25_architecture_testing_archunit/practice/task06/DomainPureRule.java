package m25_architecture_testing_archunit.practice.task06;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DomainPureRule implements ArchRule {
    @Override
    public List<String> check(Map<String, Set<String>> deps) {
        // TODO: если deps.getOrDefault("domain", Set.of()) не пуст →
        //       вернуть ["domain не должен ни от чего зависеть"]; иначе пусто
        return new ArrayList<>();
    }
}
