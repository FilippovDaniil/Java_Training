// Decorator: тот же интерфейс Logger + ссылка на обёрнутый логгер.
abstract class LoggerDecorator implements Logger {
    protected final Logger inner;

    protected LoggerDecorator(Logger inner) {
        this.inner = inner;
    }
}
