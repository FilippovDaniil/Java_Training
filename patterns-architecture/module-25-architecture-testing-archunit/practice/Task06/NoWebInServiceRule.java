import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class NoWebInServiceRule implements ArchRule {
    @Override
    public List<String> check(Map<String, Set<String>> deps) {
        // TODO: если deps.getOrDefault("service", Set.of()).contains("web") →
        //       вернуть ["service зависит от web"]; иначе пусто
        return new ArrayList<>();
    }
}
