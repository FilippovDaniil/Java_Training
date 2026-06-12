package m07_adapter_decorator.practice.task07;

/**
 * Задача 07 — Тема 07 (МИНИ-ПРОЕКТ BAM): импорт транзакций + аудит
 *
 * Развитие BAM. Объедините два структурных паттерна:
 *   - ADAPTER — привести «сырой» внешний формат фида к нашему интерфейсу;
 *   - DECORATOR — нарастить аудит-логирование поверх источника, не меняя его.
 *
 * ЗАДАНИЕ:
 *   1. ExternalBankFeed (файл ExternalBankFeed.java, менять НЕЛЬЗЯ): отдаёт
 *      сырые строки вида "5000;deposit" через List<String> fetchRaw().
 *   2. Transaction (файл Transaction.java): данные amountCents (long), type (String).
 *   3. TransactionSource (файл TransactionSource.java): List<Transaction> load();
 *   4. FeedAdapter (файл FeedAdapter.java): реализует TransactionSource, внутри
 *      ExternalBankFeed; парсит каждую строку "amount;type" в Transaction.
 *   5. AuditingSource (файл AuditingSource.java): ДЕКОРАТОР TransactionSource —
 *      оборачивает другой TransactionSource, при load() печатает "[audit] загружено N"
 *      и возвращает тот же список.
 *   В main: оберните ExternalBankFeed в FeedAdapter, затем в AuditingSource,
 *   вызовите load() и выведите транзакции.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [audit] загружено 2
 *   +5000 deposit
 *   -1200 withdrawal
 *
 * ТРЕБОВАНИЯ:
 *   - ExternalBankFeed не меняется (доступ через адаптер);
 *   - FeedAdapter только парсит формат (без бизнес-логики);
 *   - AuditingSource добавляет логирование, делегируя load() обёрнутому источнику;
 *   - и адаптер, и декоратор реализуют один интерфейс TransactionSource.
 *
 * ПОДСКАЗКА:
 *   new AuditingSource(new FeedAdapter(new ExternalBankFeed(...))). Парсинг строки:
 *   split(";") → amountCents = Long.parseLong(parts[0]), type = parts[1].
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите цепочку AuditingSource(FeedAdapter(ExternalBankFeed)),
        //       вызовите load(), выведите транзакции
    }
}
