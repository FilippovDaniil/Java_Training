package m15_layered_mvc_clean.practice.task01;

// Persistence: контракт доступа к данным.
interface ClientRepository {
    Client findById(String id);
}
