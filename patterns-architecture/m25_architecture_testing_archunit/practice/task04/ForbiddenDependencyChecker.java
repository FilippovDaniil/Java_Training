package m25_architecture_testing_archunit.practice.task04;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ForbiddenDependencyChecker {
    public List<String> check(Map<String, Set<String>> deps, String from, String forbidden) {
        // TODO: violations = new ArrayList<>();
        //       если deps.getOrDefault(from, Set.of()).contains(forbidden) →
        //         violations.add(from + " зависит от запрещённого " + forbidden);
        //       вернуть violations
        return new ArrayList<>();
    }
}
