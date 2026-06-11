package m35_logging.practice;

/**
 * Задача 07 — Модуль 35 (МИНИ-ПРОЕКТ): Логирование банковского сервиса
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: slf4j-api + logback-classic.
 *
 * ЗАДАНИЕ:
 *   Добавьте осмысленное логирование в сервис переводов BankService.
 *   Реализуйте метод transfer(from, to, amount) и расставьте логи:
 *     - INFO  при начале перевода ("Перевод {} -> {}: {}");
 *     - DEBUG с деталями (балансы до/после);
 *     - WARN  при подозрительно крупной сумме (> 1_000_000);
 *     - ERROR при ошибке (недостаточно средств — бросьте и залогируйте
 *       исключение через log.error(..., e));
 *     - используйте MDC для пометки каждого перевода уникальным txId.
 *   Настройте logback.xml: вывод в консоль и в файл bank.log, паттерн
 *   с [%X{txId}].
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [tx-1] INFO  - Перевод acc1 -> acc2: 500
 *   [tx-1] DEBUG - Баланс acc1: 1000 -> 500
 *   [tx-2] WARN  - Крупный перевод: 2000000
 *   [tx-3] ERROR - Недостаточно средств на acc1
 *
 * ЦЕЛЬ:
 *   Прочувствовать, как логи разных уровней + MDC дают полную картину
 *   работы сервиса, пригодную для расследования инцидентов.
 *
 * ПОДСКАЗКА:
 *   Комбинируйте уровни (info/debug/warn/error), параметризацию {},
 *   логирование исключения последним аргументом и MDC для txId.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Task07 {
    private static final Logger log = LoggerFactory.getLogger(Task07.class);

    public static void main(String[] args) {
        // Выполните несколько переводов с полным логированием (info/debug/warn/error + MDC)
    }

    // TODO: метод transfer(String from, String to, double amount) с логированием
}
