// Базовый декоратор: тот же интерфейс Coffee + ссылка на обёрнутый объект.
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee inner;

    protected CoffeeDecorator(Coffee inner) {
        this.inner = inner;
    }
}
