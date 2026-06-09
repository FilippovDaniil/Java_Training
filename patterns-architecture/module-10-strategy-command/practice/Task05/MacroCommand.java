import java.util.ArrayList;
import java.util.List;

// Макрос — команда из команд (перекликается с Composite).
class MacroCommand implements Command {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command command) {
        // TODO: добавить в список
    }

    @Override
    public void execute() {
        // TODO: выполнить execute() у всех команд по порядку
    }
}
