package m17_hexagonal_ports_adapters.practice.task06;

class ConsoleAuditAdapter implements AuditPort {
    @Override
    public void record(String msg) {
        // TODO: напечатать "[audit] " + msg
    }
}
