/**
 * Задача 07 — Тема 13 (МИНИ-ПРОЕКТ BAM): история транзакций + отчёт
 *
 * Развитие BAM. Объедините два поведенческих паттерна:
 *   - ITERATOR — перебор истории транзакций без знания её устройства;
 *   - VISITOR — операция-отчёт над разными типами транзакций (без правок их классов).
 *
 * ЗАДАНИЕ:
 *   1. Иерархия: интерфейс Transaction (файл Transaction.java) с
 *      void accept(TransactionVisitor v); реализации Deposit (файл Deposit.java)
 *      и Withdrawal (файл Withdrawal.java), у каждой поле amountCents.
 *   2. TransactionVisitor (файл TransactionVisitor.java): void visitDeposit(Deposit d);
 *      void visitWithdrawal(Withdrawal w).
 *   3. TransactionHistory (файл TransactionHistory.java) implements
 *      Iterable<Transaction>: add(Transaction), свой iterator().
 *   4. BalanceVisitor (файл BalanceVisitor.java): копит чистый баланс
 *      (+amount для Deposit, -amount для Withdrawal), метод net().
 *   В main: наполните историю (пара пополнений и списание), переберите её
 *   for-each (Iterator) и для каждой вызовите accept(balanceVisitor) (Visitor);
 *   выведите чистый баланс.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Чистый баланс: 17000   (deposit 10000 + deposit 12000 - withdrawal 5000)
 *
 * ТРЕБОВАНИЯ:
 *   - история перебирается через Iterator (for-each по TransactionHistory);
 *   - подсчёт баланса — в визиторе (double dispatch), не в классах транзакций;
 *   - новый отчёт (например, сумма только списаний) = новый визитор без правок
 *     Deposit/Withdrawal.
 *
 * ПОДСКАЗКА:
 *   for (Transaction t : history) t.accept(balanceVisitor); — Iterator даёт
 *   элементы, Visitor разносит их по типам. В теме 20 такой обход событий
 *   строит CQRS-проекции.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: наполните TransactionHistory, обойдите for-each с accept(BalanceVisitor),
        //       выведите net()
    }
}
