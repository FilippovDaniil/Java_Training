// Сторонний датчик (готов, менять НЕЛЬЗЯ): отдаёт температуру в Фаренгейтах.
class FahrenheitSensor {
    private final double fahrenheit;

    FahrenheitSensor(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    double readFahrenheit() {
        return fahrenheit;
    }
}
