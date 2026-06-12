package m25_architecture_testing_archunit.practice.task01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Проверяет правило слоёв на графе зависимостей.
class LayerChecker {
    public List<String> check(Map<String, Set<String>> deps) {
        // TODO: violations = new ArrayList<>();
        //       Set<String> domainDeps = deps.getOrDefault("domain", Set.of());
        //       для каждого запрещённого ("web", "infrastructure"):
        //         если domainDeps.contains(forbidden) → violations.add("domain зависит от " + forbidden);
        //       вернуть violations
        return new ArrayList<>();
    }
}
