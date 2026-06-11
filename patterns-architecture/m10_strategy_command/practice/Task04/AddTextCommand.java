package m10_strategy_command.practice.task04;

class AddTextCommand implements Command {
    // TODO: поля TextDocument doc и String text + конструктор AddTextCommand(TextDocument doc, String text)

    @Override
    public void execute() {
        // TODO: doc.append(text)
    }

    @Override
    public void undo() {
        // TODO: doc.deleteLast(text.length())
    }
}
