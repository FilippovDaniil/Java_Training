package m21_saga_distributed_transactions.practice.task07;

/**
 * Задача 07 — Тема 21 (МИНИ-ПРОЕКТ BAM): saga перевода с компенсацией
 *
 * Развитие BAM. Перевод между счетами как сага-оркестрация: списать → зачислить,
 * а при сбое зачисления — компенсировать списание (вернуть деньги). Общей
 * транзакции нет — согласованность держим компенсацией.
 *
 * ЗАДАНИЕ:
 *   1. Account (файл Account.java): id, balanceCents; debit(amount) (списать,
 *      хватает средств иначе IllegalStateException), credit(amount) (зачислить);
 *      balance(), id().
 *   2. SagaStep (файл SagaStep.java): execute(); compensate().
 *   3. DebitStep (файл DebitStep.java): execute → account.debit(amount) и печать
 *      "списано <amount> со счёта <id>"; compensate → account.credit(amount) и
 *      печать "возврат <amount> на счёт <id>".
 *   4. CreditStep (файл CreditStep.java): хранит флаг willFail; execute → если
 *      willFail печатает "зачисление на <id> не удалось" и бросает RuntimeException;
 *      иначе account.credit и печать "зачислено <amount> на <id>";
 *      compensate → account.debit(amount).
 *   5. TransferSaga (файл TransferSaga.java): run(List<SagaStep>) — оркестрация с
 *      откатом успешных шагов в обратном порядке при сбое.
 *   В main: A=10000, B=0; запустите сагу [Debit A 3000, Credit B 3000 (willFail=true)];
 *   зачисление упадёт → списание компенсируется; выведите итоговые балансы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   списано 3000 со счёта A
 *   зачисление на B не удалось
 *   возврат 3000 на счёт A
 *   Итог: A=10000, B=0
 *
 * ТРЕБОВАНИЯ:
 *   - при сбое зачисления списание компенсируется (деньги не «исчезают»);
 *   - компенсации — в обратном порядке выполненных шагов;
 *   - итог согласован: A вернулся к 10000, B остался 0 (eventual consistency).
 *
 * ПОДСКАЗКА:
 *   Это сводит воедино тему: шаги с компенсациями (02) + оркестрацию с откатом (03)
 *   на реальном доменном эффекте (баланс). Без саги «частичный» перевод оставил бы
 *   деньги списанными, но не зачисленными.
 */

import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        // TODO: A=10000, B=0; TransferSaga.run(List.of(new DebitStep(a,3000),
        //       new CreditStep(b,3000,true))); выведите "Итог: A=.., B=.."
    }
}
