// Детерминированный двойник для теста: всегда возвращает заданное число.
class FixedNumberSource implements NumberSource {
    // TODO: поле final int value + конструктор FixedNumberSource(int value)

    @Override
    public int next() {
        // TODO: вернуть value
        return 0;
    }
}
