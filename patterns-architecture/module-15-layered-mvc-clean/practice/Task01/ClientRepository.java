// Persistence: контракт доступа к данным.
interface ClientRepository {
    Client findById(String id);
}
