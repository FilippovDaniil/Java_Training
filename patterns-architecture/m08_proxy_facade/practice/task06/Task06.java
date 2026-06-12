package m08_proxy_facade.practice.task06;

/**
 * Задача 06 — Тема 08: Proxy + Facade вместе
 *
 * ЗАДАНИЕ:
 *   Фасад прячет подсистему, а прокси добавляет логирование перед вызовом фасада.
 *     - интерфейс ReportService (файл ReportService.java): String generate(String name);
 *     - подсистема: DataFetcher (файл DataFetcher.java) String fetch(String name)
 *       → "данные(name)"; Formatter (файл Formatter.java) String format(String raw)
 *       → "<<raw>>";
 *     - RealReportFacade (файл RealReportFacade.java) реализует ReportService:
 *       generate() координирует DataFetcher → Formatter;
 *     - LoggingReportProxy (файл LoggingReportProxy.java) реализует ReportService,
 *       оборачивает другой ReportService: печатает "[log] generate(name)" и
 *       делегирует.
 *   В main оберните RealReportFacade в LoggingReportProxy и сгенерируйте отчёт.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [log] generate(orders)
 *   <<данные(orders)>>
 *
 * ТРЕБОВАНИЯ:
 *   - Facade (RealReportFacade) скрывает координацию DataFetcher+Formatter;
 *   - Proxy (LoggingReportProxy) добавляет логирование, не меняя фасад;
 *   - и фасад, и прокси реализуют один интерфейс ReportService.
 *
 * ПОДСКАЗКА:
 *   new LoggingReportProxy(new RealReportFacade()). Прокси и фасад
 *   взаимозаменяемы для клиента — оба ReportService.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: оберните RealReportFacade в LoggingReportProxy, вызовите generate("orders")
    }
}
