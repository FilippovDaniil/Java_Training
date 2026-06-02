/**
 * Задача 06 — Модуль 53: Выбор стратегии наследования под сценарий
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Ниже описаны три бизнес-сценария. Для каждого:
 *     а) выберите наиболее подходящую стратегию наследования;
 *     б) обоснуйте выбор (заполните комментарии ANSWER_X);
 *     в) реализуйте сценарий C кодом и докажите работоспособность через H2.
 *
 * СРАВНИТЕЛЬНАЯ ТАБЛИЦА (повторение из theory.md):
 * ┌──────────────────┬──────────────┬────────────┬─────────────────┐
 * │ Критерий         │ SINGLE_TABLE │   JOINED   │ TABLE_PER_CLASS │
 * ├──────────────────┼──────────────┼────────────┼─────────────────┤
 * │ NULL-поля        │ много        │ нет        │ нет             │
 * │ JOIN на чтение   │ нет          │ да         │ нет (UNION)     │
 * │ Полиморф. запрос │ быстро       │ средне     │ медленно        │
 * │ FK на иерархию   │ легко        │ легко      │ невозможно      │
 * │ Нормализация     │ низкая       │ высокая    │ средняя         │
 * └──────────────────┴──────────────┴────────────┴─────────────────┘
 *
 * СЦЕНАРИЙ A:
 *   Интернет-магазин. Иерархия: Vehicle → Car, Truck, Motorcycle.
 *   У Car есть 5 уникальных полей, у Truck — 8, у Motorcycle — 3.
 *   Часто выполняется запрос "найти все Vehicle дешевле 1 000 000 руб."
 *   Приложение читает данные намного чаще, чем пишет (read-heavy).
 *
 * СЦЕНАРИЙ B:
 *   Банковская система. Иерархия: Account → SavingsAccount, CheckingAccount, LoanAccount.
 *   Строгие требования нормализации, аудит изменений.
 *   У каждого подтипа 10-15 уникальных полей.
 *   Часто нужны FK из таблицы Transactions на Account.
 *
 * СЦЕНАРИЙ C (РЕАЛИЗУЙТЕ):
 *   Система уведомлений. Иерархия: Notification → EmailNotification, SmsNotification.
 *   EmailNotification: subject (String), body (String), recipientEmail (String).
 *   SmsNotification: phoneNumber (String), messageText (String).
 *   Требование: максимальная простота, изоляция подтипов — FK на Notification НЕ нужны,
 *   полиморфные запросы почти не используются.
 *   Подсказка: какая стратегия даёт полную изоляцию и отсутствие NULL?
 *
 * ПОДСКАЗКА:
 *   Для Сценария A — подумайте, что важнее: производительность чтения или нормализация?
 *   Для Сценария B — FK на Account возможен только при SINGLE_TABLE или JOINED.
 *   Для Сценария C — TABLE_PER_CLASS даёт изоляцию, но теряет полиморфизм.
 *     Альтернатива — @MappedSuperclass, если полиморфизм JPA совсем не нужен.
 */
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.util.List;

public class Task06 {

    /*
     * ANSWER_A: Выбранная стратегия для Сценария A: [???]
     * Обоснование: TODO
     */

    /*
     * ANSWER_B: Выбранная стратегия для Сценария B: [???]
     * Обоснование: TODO
     */

    /*
     * ANSWER_C: Выбранная стратегия для Сценария C: [???]
     * Обоснование: TODO
     */

    public static void main(String[] args) {
        // TODO: реализуйте Сценарий C:
        //   1) создайте SessionFactory для Notification, EmailNotification, SmsNotification
        //   2) сохраните 2 EmailNotification и 1 SmsNotification
        //   3) загрузите их обратно и выведите тип и ключевые поля
        //   4) выведите SQL из show_sql и убедитесь в корректности маппинга
    }
}

// --- Базовый класс уведомлений (дополните аннотациями и выберите стратегию) ---

@Entity
// TODO: добавьте @Inheritance(strategy = ...) — выберите стратегию для Сценария C
abstract class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) // TABLE — безопасно для TABLE_PER_CLASS
    private Long id;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(nullable = false)
    private String status; // PENDING, SENT, FAILED

    // TODO: конструктор(status), геттеры, toString()
}

// --- EmailNotification (дополните @Entity @Table) ---

// TODO: добавьте @Entity и @Table(name = "email_notifications")
class EmailNotification extends Notification {
    private String subject;
    private String body;
    private String recipientEmail;

    // TODO: конструктор(subject, body, recipientEmail, status), геттеры, toString()
}

// --- SmsNotification (дополните @Entity @Table) ---

// TODO: добавьте @Entity и @Table(name = "sms_notifications")
class SmsNotification extends Notification {
    private String phoneNumber;
    private String messageText;

    // TODO: конструктор(phoneNumber, messageText, status), геттеры, toString()
}
