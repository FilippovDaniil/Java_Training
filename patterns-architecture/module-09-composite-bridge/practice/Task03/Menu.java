import java.util.ArrayList;
import java.util.List;

class Menu implements MenuComponent {
    // TODO: поле name + список List<MenuComponent> children + конструктор Menu(String name)

    public void add(MenuComponent component) {
        // TODO: добавить в children
    }

    @Override
    public String render(int indent) {
        // TODO: своя строка ("  ".repeat(indent) + name + "\n"),
        //       затем для каждого ребёнка child.render(indent + 1)
        return "";
    }
}
