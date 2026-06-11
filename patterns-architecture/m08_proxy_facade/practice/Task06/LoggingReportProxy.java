package m08_proxy_facade.practice.task06;

// Proxy: логирует вызов и делегирует обёрнутому ReportService (например, фасаду).
class LoggingReportProxy implements ReportService {
    // TODO: поле ReportService inner + конструктор LoggingReportProxy(ReportService inner)

    @Override
    public String generate(String name) {
        // TODO: напечатать "[log] generate(" + name + ")", затем вернуть inner.generate(name)
        return "";
    }
}
