package m17_hexagonal_ports_adapters.practice.task06;

// Выходной порт №2: аудит.
interface AuditPort {
    void record(String msg);
}
