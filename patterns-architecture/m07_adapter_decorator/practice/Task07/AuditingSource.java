package m07_adapter_decorator.practice.task07;

import java.util.List;

// Decorator: оборачивает любой TransactionSource и добавляет аудит-лог.
class AuditingSource implements TransactionSource {
    // TODO: поле TransactionSource inner + конструктор AuditingSource(TransactionSource inner)

    @Override
    public List<Transaction> load() {
        // TODO: result = inner.load(); напечатать "[audit] загружено " + result.size();
        //       вернуть result
        return List.of();
    }
}
