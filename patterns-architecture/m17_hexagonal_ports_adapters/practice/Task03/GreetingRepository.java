package m17_hexagonal_ports_adapters.practice.task03;

// Выходной (driven) порт.
interface GreetingRepository {
    String find(String lang);
}
