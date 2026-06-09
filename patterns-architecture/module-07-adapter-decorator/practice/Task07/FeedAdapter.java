import java.util.ArrayList;
import java.util.List;

// Adapter: приводит ExternalBankFeed к TransactionSource, парся "amount;type".
class FeedAdapter implements TransactionSource {
    // TODO: поле ExternalBankFeed feed + конструктор FeedAdapter(ExternalBankFeed feed)

    @Override
    public List<Transaction> load() {
        // TODO: для каждой сырой строки feed.fetchRaw(): split(";"),
        //       Long.parseLong(parts[0]) и parts[1] → new Transaction(...)
        return new ArrayList<>();
    }
}
