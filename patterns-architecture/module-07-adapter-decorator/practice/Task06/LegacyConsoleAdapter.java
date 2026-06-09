// Adapter: приводит LegacyConsole к интерфейсу Logger.
class LegacyConsoleAdapter implements Logger {
    // TODO: поле LegacyConsole console + конструктор

    @Override
    public void log(String msg) {
        // TODO: делегировать console.writeLine(msg)
    }
}
