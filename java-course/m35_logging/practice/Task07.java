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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Task07 {
    private static final Logger log = LoggerFactory.getLogger(Task07.class);

    // Хранилище балансов счетов
    private static final Map<String, Double> accounts = new HashMap<>();

    static {
        // Инициализация счетов
        accounts.put("acc1", 1500.0);
        accounts.put("acc2", 500.0);
        accounts.put("acc3", 2500.0);
        accounts.put("acc4", 100.0);
        accounts.put("acc5", 5000000.0);
    }

    public static void main(String[] args) {
        System.out.println("=== БАНКОВСКИЙ СЕРВИС ===\n");

        // Успешные переводы
        transfer("acc1", "acc2", 500.0);
        transfer("acc3", "acc1", 200.0);

        // Крупный перевод (WARN)
        transfer("acc5", "acc2", 1500000.0);

        // Ошибка - недостаточно средств
        transfer("acc4", "acc1", 1000.0);

        // Крупный перевод с ошибкой
        transfer("acc1", "acc3", 2000000.0);

        // Несколько переводов с одного счета
        transfer("acc3", "acc4", 300.0);
        transfer("acc3", "acc1", 100.0);

        // Перевод между несуществующими счетами
        transfer("acc999", "acc1", 100.0);

        // Некорректная сумма
        transfer("acc1", "acc2", -50.0);

        System.out.println("\n✅ Проверьте логи в файле bank.log");
        System.out.println("📁 Файл bank.log находится в корне проекта");
    }

    /**
     * Метод перевода средств между счетами
     */
    public static void transfer(String from, String to, double amount) {
        // Генерируем уникальный ID для перевода
        String txId = "tx-" + UUID.randomUUID().toString().substring(0, 8);

        try {
            // Устанавливаем txId в MDC
            MDC.put("txId", txId);
            MDC.put("from", from);
            MDC.put("to", to);

            log.info("📤 Начало перевода: {} -> {} на сумму {}", from, to, amount);

            // Проверка отрицательной суммы
            if (amount < 0) {
                log.error("❌ Попытка перевода отрицательной суммы: {} от {} -> {}", amount, from, to);
                throw new IllegalArgumentException("Сумма перевода должна быть положительной");
            }

            // Проверка существования счетов
            if (!accounts.containsKey(from)) {
                log.error("❌ Счет отправителя {} не найден", from);
                throw new IllegalArgumentException("Счет отправителя не найден: " + from);
            }

            if (!accounts.containsKey(to)) {
                log.error("❌ Счет получателя {} не найден", to);
                throw new IllegalArgumentException("Счет получателя не найден: " + to);
            }

            // Получаем текущие балансы
            double fromBalance = accounts.get(from);
            double toBalance = accounts.get(to);

            log.debug("💰 Баланс до перевода: {} = {}, {} = {}", from, fromBalance, to, toBalance);

            // Проверка крупной суммы
            if (amount > 1_000_000) {
                log.warn("⚠️ КРУПНЫЙ ПЕРЕВОД: {} на сумму {} от {} -> {}", txId, amount, from, to);
                log.warn("⚠️ Требуется дополнительная проверка для перевода на сумму {}", amount);
            }

            // Проверка достаточности средств
            if (fromBalance < amount) {
                log.error("❌ Недостаточно средств на счете {}. Баланс: {}, требуется: {}",
                        from, fromBalance, amount);
                throw new IllegalStateException(
                        String.format("Недостаточно средств на счете %s. Баланс: %.2f, требуется: %.2f",
                                from, fromBalance, amount)
                );
            }

            // Выполняем перевод
            double newFromBalance = fromBalance - amount;
            double newToBalance = toBalance + amount;

            // Логируем детали перевода
            log.debug("🔄 Обновление балансов: {} ({:.2f} -> {:.2f}), {} ({:.2f} -> {:.2f})",
                    from, fromBalance, newFromBalance, to, toBalance, newToBalance);

            // Сохраняем новые балансы
            accounts.put(from, newFromBalance);
            accounts.put(to, newToBalance);

            // Логируем успешный перевод
            log.info("✅ Перевод {} завершен успешно: {} -> {} на сумму {}",
                    txId, from, to, amount);
            log.debug("💰 Баланс после перевода: {} = {}, {} = {}",
                    from, newFromBalance, to, newToBalance);

            // Если остаток на счете маленький
            if (newFromBalance < 100) {
                log.warn("⚠️ Низкий остаток на счете {}: {:.2f}", from, newFromBalance);
            }

        } catch (Exception e) {
            log.error("❌ Ошибка при переводе {}: {}", txId, e.getMessage(), e);
        } finally {
            // Очищаем MDC
            MDC.remove("txId");
            MDC.remove("from");
            MDC.remove("to");
        }

        // Небольшая пауза для наглядности
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
