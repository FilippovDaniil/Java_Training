package m16_dependency_injection_ioc.practice.task01;

// Зависимость внедряется через конструктор, не создаётся внутри.
class Greeter {
    // TODO: поле final MessageService service + конструктор Greeter(MessageService service)

    public String greet(String name) {
        // TODO: делегировать service.message(name)
        return "";
    }
}
