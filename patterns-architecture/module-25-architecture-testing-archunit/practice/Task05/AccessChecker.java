import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Проверяет правило «к target обращается только allowedAccessor».
class AccessChecker {
    public List<String> check(Map<String, Set<String>> deps, String target, String allowedAccessor) {
        // TODO: для каждой пары (component, его зависимости):
        //   если зависимости содержат target И component != allowedAccessor →
        //     добавить component + " обращается к " + target + " (разрешено только " + allowedAccessor + ")"
        return new ArrayList<>();
    }
}
