package m35_logging.practice.task05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PaymentService {
    // Свой логгер для PaymentService
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    public void pay(double amount) {
        log.debug("Начало обработки платежа на сумму: {}", amount);

        // Имитация валидации
        if (amount <= 0) {
            log.error("Попытка оплаты с некорректной суммой: {}", amount);
            return;
        }

        if (amount > 10000) {
            log.warn("Крупный платеж на сумму: {} (требуется дополнительная проверка)", amount);
        }

        // Имитация обработки платежа
        log.info("Платёж проведён: {}", amount);

        if (amount > 100000) {
            log.warn("Очень крупный платеж: {} (проверка на мошенничество)", amount);
        }

        log.debug("Платеж на сумму {} успешно обработан", amount);
    }

    // Дополнительный метод для демонстрации
    public void refund(double amount) {
        log.info("Возврат платежа на сумму: {}", amount);
        if (amount < 0) {
            log.error("Некорректная сумма возврата: {}", amount);
        }
    }
}