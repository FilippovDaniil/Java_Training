package m03_solid_lsp_isp_dip.practice.task07;

class Account {
    // TODO: приватные поля id (String) и balanceCents (long)

    // TODO: конструктор Account(String id, long initialBalanceCents)

    public String getId() {
        // TODO
        return "";
    }

    public long getBalanceCents() {
        // TODO
        return 0;
    }

    public void deposit(long amountCents) {
        // TODO: проверить amountCents > 0, увеличить баланс
    }

    public void withdraw(long amountCents) {
        // TODO: проверить amountCents > 0 и достаточность; иначе IllegalStateException;
        //       уменьшить баланс
    }
}
