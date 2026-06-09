import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Проверяет Dependency Rule гексагона: ядро не зависит от адаптеров.
class HexagonArchTest {
    public List<String> verify(Map<String, Set<String>> deps) {
        // TODO: violations = new ArrayList<>();
        //       Set<String> coreDeps = deps.getOrDefault("core", Set.of());
        //       для adapter в {"web_adapter","db_adapter"}:
        //         если coreDeps.contains(adapter) → violations.add("core зависит от адаптера " + adapter);
        //       вернуть violations
        return new ArrayList<>();
    }
}
