import java.util.HashMap;
import java.util.Map;

class LocalAccountService implements AccountService {
    private final Map<String, Long> balances = new HashMap<>();

    public void seed(String accountId, long balance) {
        // TODO: положить баланс
    }

    @Override
    public long balance(String accountId) {
        // TODO: вернуть баланс (0, если нет)
        return 0;
    }
}
