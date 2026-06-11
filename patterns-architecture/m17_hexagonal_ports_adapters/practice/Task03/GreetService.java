package m17_hexagonal_ports_adapters.practice.task03;

// Ядро: реализует входной порт, использует выходной. Адаптеров не знает.
class GreetService implements GreetUseCase {
    // TODO: поле final GreetingRepository repo + конструктор GreetService(GreetingRepository repo)

    @Override
    public String greet(String lang, String name) {
        // TODO: repo.find(lang) + ", " + name
        return "";
    }
}
