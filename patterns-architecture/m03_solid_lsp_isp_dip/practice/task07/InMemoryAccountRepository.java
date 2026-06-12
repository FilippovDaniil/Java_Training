package m03_solid_lsp_isp_dip.practice.task07;

class InMemoryAccountRepository implements AccountRepository {
    // TODO: приватное поле Map<String, Account> store

    @Override
    public void save(Account account) {
        // TODO: положить по account.getId()
    }

    @Override
    public Account findById(String id) {
        // TODO: вернуть по id (или null)
        return null;
    }
}
