package m11_observer_chain_of_responsibility.practice.task07;

/**
 * Задача 07 — Тема 11 (МИНИ-ПРОЕКТ BAM): проводка транзакции + уведомления
 *
 * Развитие BAM. Объедините два поведенческих паттерна:
 *   - CHAIN OF RESPONSIBILITY — конвейер валидации транзакции перед проводкой;
 *   - OBSERVER — после успешной проводки уведомить подписчиков (аудит, алерты).
 *
 * ЗАДАНИЕ:
 *   1. Account (файл Account.java): balanceCents; apply(long delta); getBalanceCents().
 *   2. Validator (файл Validator.java): next, setNext, abstract String validate(long amount),
 *      passToNext (вернуть "ok", если next нет); реализации:
 *      NonZeroValidator (amount != 0, иначе "нулевая сумма") и
 *      LimitValidator (|amount| <= 100000, иначе "превышен лимит").
 *   3. TransactionListener (файл TransactionListener.java) — Observer:
 *      void onPosted(long amount, long newBalance); реализации AuditListener
 *      ("[audit] проведено <amount>, баланс <newBalance>") и BigAmountListener
 *      ("[alert] крупная сумма <amount>" если |amount| >= 50000).
 *   4. TransactionService (файл TransactionService.java): держит Account, голову
 *      цепочки валидации и список слушателей; subscribe(listener);
 *      post(long amount): валидировать (CoR) → при "ok" применить к счёту и
 *      уведомить слушателей (Observer); иначе вывести "отклонено: <причина>".
 *   В main: подпишите оба listener, проведите валидную сумму, крупную сумму и
 *   нулевую (отказ).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [audit] проведено 30000, баланс 30000
 *   [audit] проведено 60000, баланс 90000
 *   [alert] крупная сумма 60000
 *   отклонено: нулевая сумма
 *
 * ТРЕБОВАНИЯ:
 *   - транзакция проводится только если прошла ВСЮ цепочку валидации (CoR);
 *   - после проводки уведомляются ВСЕ подписчики (Observer);
 *   - при отказе валидации баланс не меняется и слушатели не уведомляются.
 *
 * ПОДСКАЗКА:
 *   CoR отвечает «можно ли проводить», Observer — «кого известить о проводке».
 *   В теме 19 это станет доменными событиями, в теме 24 — событиями в Kafka.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите TransactionService (Account + цепочка валидаторов),
        //       подпишите AuditListener и BigAmountListener, проведите 30000, 60000, 0
    }
}
