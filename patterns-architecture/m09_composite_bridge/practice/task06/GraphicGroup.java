package m09_composite_bridge.practice.task06;

import java.util.ArrayList;
import java.util.List;

// Композит: группа графики; склеивает render() своих детей.
class GraphicGroup implements Graphic {
    private final List<Graphic> children = new ArrayList<>();

    public void add(Graphic graphic) {
        // TODO: добавить в children
    }

    @Override
    public String render() {
        // TODO: склеить render() всех детей (StringBuilder)
        return "";
    }
}
