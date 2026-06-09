class ConsoleAuditAdapter implements AuditPort {
    @Override
    public void record(String msg) {
        // TODO: напечатать "[audit] " + msg
    }
}
