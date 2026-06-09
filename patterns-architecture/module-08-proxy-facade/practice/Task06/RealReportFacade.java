// Facade: скрывает координацию подсистемы DataFetcher → Formatter.
class RealReportFacade implements ReportService {
    private final DataFetcher fetcher = new DataFetcher();
    private final Formatter formatter = new Formatter();

    @Override
    public String generate(String name) {
        // TODO: raw = fetcher.fetch(name); вернуть formatter.format(raw)
        return "";
    }
}
