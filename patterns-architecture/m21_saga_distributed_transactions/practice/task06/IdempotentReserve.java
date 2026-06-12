package m21_saga_distributed_transactions.practice.task06;

import java.util.HashSet;
import java.util.Set;

// Идемпотентный шаг: повторная обработка того же id не дублирует эффект.
class IdempotentReserve {
    private final Set<String> seen = new HashSet<>();

    public void reserve(String orderId) {
        // TODO: если orderId уже в seen → напечатать "уже зарезервировано: " + orderId;
        //       иначе добавить в seen и напечатать "резерв: " + orderId
    }
}
