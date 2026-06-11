package m23_microservices_vs_monolith.practice.task04;

class ConsoleAuditService implements AuditService {
    @Override
    public void record(String msg) {
        // TODO: напечатать "[audit] " + msg
    }
}
