package m09_composite_bridge.practice.task02;

import java.util.ArrayList;
import java.util.List;

class Department implements OrgUnit {
    private final List<OrgUnit> units = new ArrayList<>();

    public void add(OrgUnit unit) {
        // TODO: добавить в units
    }

    @Override
    public int headcount() {
        // TODO: рекурсивно просуммировать headcount() всех units
        return 0;
    }
}
