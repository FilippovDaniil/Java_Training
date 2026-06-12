package m15_layered_mvc_clean.practice.task02;

class ClientService {
    // Маппинг на границе слоёв: domain → DTO (без internalNote).
    public ClientDto toDto(Client client) {
        // TODO: вернуть new ClientDto(client.getId(), client.getName())
        return null;
    }
}
