package m15_layered_mvc_clean.practice.task01;

// Presentation: принимает запрос, делегирует сервису (не лезет в репозиторий).
class ClientController {
    // TODO: поле ClientService service + конструктор ClientController(ClientService service)

    public String show(String id) {
        // TODO: делегировать service.describe(id)
        return "";
    }
}
