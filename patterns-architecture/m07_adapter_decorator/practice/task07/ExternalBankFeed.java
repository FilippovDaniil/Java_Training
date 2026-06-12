package m07_adapter_decorator.practice.task07;

import java.util.List;

// Внешний фид (готов, менять НЕЛЬЗЯ): отдаёт сырые строки "amount;type".
class ExternalBankFeed {
    private final List<String> raw;

    ExternalBankFeed(List<String> raw) {
        this.raw = List.copyOf(raw);
    }

    List<String> fetchRaw() {
        return raw;
    }
}
