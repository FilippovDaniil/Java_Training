package m16_dependency_injection_ioc.practice.task03;

/**
 * Задача 03 — Тема 16: Constructor vs Setter injection
 *
 * ЗАДАНИЕ:
 *   Покажите оба вида внедрения: обязательную зависимость — через конструктор,
 *   необязательную — через сеттер.
 *     - DataSource (файл DataSource.java): String data(); StaticDataSource
 *       возвращает "данные" (ОБЯЗАТЕЛЬНАЯ зависимость);
 *     - Logger (файл Logger.java): void log(String); ConsoleLogger печатает
 *       "[log] ..." (НЕОБЯЗАТЕЛЬНАЯ зависимость);
 *     - ReportGenerator (файл ReportGenerator.java): DataSource через конструктор
 *       (final), Logger через setLogger(...) (по умолчанию null); generate()
 *       берёт data() и, ЕСЛИ logger задан, логирует.
 *   В main: сгенерируйте отчёт БЕЗ логгера (лога нет), затем задайте логгер
 *   сеттером и сгенерируйте снова (лог появился).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Отчёт: данные
 *   [log] сгенерирован отчёт
 *   Отчёт: данные
 *
 * ТРЕБОВАНИЯ:
 *   - DataSource обязателен → конструктор + final;
 *   - Logger опционален → сеттер, может остаться null (тогда не логируем);
 *   - generate() работает и без логгера (никаких NPE).
 *
 * ПОДСКАЗКА:
 *   Constructor injection — для того, без чего объект бессмысленен; setter —
 *   для «приятных дополнений». Проверяйте logger на null перед вызовом.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: ReportGenerator с DataSource (без логгера) → generate();
        //       setLogger(ConsoleLogger) → generate()
    }
}
