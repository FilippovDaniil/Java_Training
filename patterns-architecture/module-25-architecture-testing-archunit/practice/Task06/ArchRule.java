import java.util.List;
import java.util.Map;
import java.util.Set;

interface ArchRule {
    List<String> check(Map<String, Set<String>> deps);
}
