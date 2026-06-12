package m11_observer_chain_of_responsibility.practice.task07;

import java.util.ArrayList;
import java.util.List;

// Связка CoR (валидация) + Observer (уведомления о проводке).
class TransactionService {
    private final Account account;
    private final Validator validationHead;
    private final List<TransactionListener> listeners = new ArrayList<>();

    public TransactionService(Account account, Validator validationHead) {
        this.account = account;
        this.validationHead = validationHead;
    }

    public void subscribe(TransactionListener listener) {
        // TODO: добавить слушателя
    }

    public void post(long amount) {
        // TODO: 1) result = validationHead.validate(amount);
        //       2) если "ok" — account.apply(amount) и уведомить всех listeners
        //          (onPosted(amount, account.getBalanceCents()));
        //       3) иначе — напечатать "отклонено: " + result (баланс не меняется)
    }
}
