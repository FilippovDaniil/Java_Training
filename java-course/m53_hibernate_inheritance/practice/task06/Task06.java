package m53_hibernate_inheritance.practice.task06;

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
     * ANSWER_A: Выбранная стратегия для Сценария A: SINGLE_TABLE
     * Обоснование:
     * - Приложение read-heavy (много чтений, мало записей)
     * - Частые полиморфные запросы "найти все Vehicle дешевле..."
     * - SINGLE_TABLE даёт максимальную скорость полиморфных запросов (без JOIN)
     * - NULL-поля допустимы, т.к. у разных типов разные поля
     * - Чтение значительно важнее нормализации в этом сценарии
     * - Несмотря на NULL, производительность важнее
     */

    /*
     * ANSWER_B: Выбранная стратегия для Сценария B: JOINED
     * Обоснование:
     * - Строгие требования нормализации (банковская система)
     * - У каждого подтипа 10-15 уникальных полей (много NULL при SINGLE_TABLE)
     * - Нужны FK из таблицы Transactions на Account (JOINED поддерживает FK)
     * - Аудит и целостность данных критичны
     * - JOINED обеспечивает нормализованную структуру без NULL
     * - Медленнее при чтении, но это допустимо для банковской системы
     */

    /*
     * ANSWER_C: Выбранная стратегия для Сценария C: TABLE_PER_CLASS
     * Обоснование:
     * - Максимальная простота и изоляция подтипов
     * - Полиморфные запросы почти не используются
     * - FK на Notification не нужны
     * - Каждая таблица содержит все поля (родительские + свои)
     * - Нет NULL-полей
     * - TABLE_PER_CLASS даёт полную изоляцию подтипов
     * - Можно также использовать @MappedSuperclass, но TABLE_PER_CLASS
     *   даёт больше гибкости при минимальных затратах
     */

    public static void main(String[] args) {
        // Создаем SessionFactory для Сценария C
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:notifications;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.addAnnotatedClass(Notification.class);
        config.addAnnotatedClass(EmailNotification.class);
        config.addAnnotatedClass(SmsNotification.class);

        try (SessionFactory factory = config.buildSessionFactory()) {
            System.out.println("=== СЦЕНАРИЙ C: СИСТЕМА УВЕДОМЛЕНИЙ (TABLE_PER_CLASS) ===\n");

            // ===== СОХРАНЕНИЕ УВЕДОМЛЕНИЙ =====
            System.out.println("--- СОХРАНЕНИЕ УВЕДОМЛЕНИЙ ---");

            try (Session session = factory.openSession()) {
                session.getTransaction().begin();

                // Создаем Email уведомления
                EmailNotification email1 = new EmailNotification(
                        "Добро пожаловать!",
                        "Спасибо за регистрацию в нашем сервисе.",
                        "user@example.com",
                        "SENT"
                );

                EmailNotification email2 = new EmailNotification(
                        "Восстановление пароля",
                        "Для восстановления пароля перейдите по ссылке...",
                        "user2@example.com",
                        "PENDING"
                );

                // Создаем SMS уведомление
                SmsNotification sms1 = new SmsNotification(
                        "+7 999 123-45-67",
                        "Ваш код подтверждения: 1234",
                        "SENT"
                );

                session.persist(email1);
                session.persist(email2);
                session.persist(sms1);

                System.out.println("   ✅ Сохранены уведомления:");
                System.out.println("      - Email 1: " + email1);
                System.out.println("      - Email 2: " + email2);
                System.out.println("      - SMS 1: " + sms1);

                session.getTransaction().commit();
            }

            // ===== ЗАГРУЗКА УВЕДОМЛЕНИЙ =====
            System.out.println("\n--- ЗАГРУЗКА УВЕДОМЛЕНИЙ ---");

            try (Session session = factory.openSession()) {
                // Загружаем все уведомления (полиморфный запрос)
                List<Notification> notifications = session
                        .createQuery("FROM Notification ORDER BY id", Notification.class)
                        .getResultList();

                System.out.println("   Всего уведомлений: " + notifications.size() + "\n");

                for (Notification n : notifications) {
                    if (n instanceof EmailNotification email) {
                        System.out.printf("   📧 Email: id=%d, статус=%s, тема='%s', получатель=%s%n",
                                email.getId(), email.getStatus(), email.getSubject(), email.getRecipientEmail());
                    } else if (n instanceof SmsNotification sms) {
                        System.out.printf("   📱 SMS: id=%d, статус=%s, телефон=%s, текст='%s'%n",
                                sms.getId(), sms.getStatus(), sms.getPhoneNumber(), sms.getMessageText());
                    }
                }
            }

            // ===== ЗАГРУЗКА КОНКРЕТНЫХ ТИПОВ =====
            System.out.println("\n--- ЗАГРУЗКА КОНКРЕТНЫХ ТИПОВ ---");

            try (Session session = factory.openSession()) {
                // Загружаем только Email
                List<EmailNotification> emails = session
                        .createQuery("FROM EmailNotification", EmailNotification.class)
                        .getResultList();

                System.out.println("   Email уведомлений: " + emails.size());
                for (EmailNotification email : emails) {
                    System.out.printf("      - %s (статус: %s)%n", email.getSubject(), email.getStatus());
                }

                // Загружаем только SMS
                List<SmsNotification> smsList = session
                        .createQuery("FROM SmsNotification", SmsNotification.class)
                        .getResultList();

                System.out.println("   SMS уведомлений: " + smsList.size());
                for (SmsNotification sms : smsList) {
                    System.out.printf("      - %s (статус: %s)%n", sms.getMessageText(), sms.getStatus());
                }
            }

            // ===== ФИЛЬТРАЦИЯ ПО СТАТУСУ =====
            System.out.println("\n--- ФИЛЬТРАЦИЯ ПО СТАТУСУ ---");

            try (Session session = factory.openSession()) {
                // Все SENT уведомления
                List<Notification> sent = session
                        .createQuery("FROM Notification n WHERE n.status = :status", Notification.class)
                        .setParameter("status", "SENT")
                        .getResultList();

                System.out.println("   Отправленные уведомления (SENT): " + sent.size());
                for (Notification n : sent) {
                    System.out.printf("      - %s (id=%d)%n",
                            n.getClass().getSimpleName(), n.getId());
                }

                // Все PENDING уведомления
                List<Notification> pending = session
                        .createQuery("FROM Notification n WHERE n.status = :status", Notification.class)
                        .setParameter("status", "PENDING")
                        .getResultList();

                System.out.println("   Ожидающие уведомления (PENDING): " + pending.size());
                for (Notification n : pending) {
                    System.out.printf("      - %s (id=%d)%n",
                            n.getClass().getSimpleName(), n.getId());
                }
            }

            // ===== СТАТИСТИКА ПО ТИПАМ =====
            System.out.println("\n--- СТАТИСТИКА ПО ТИПАМ ---");

            try (Session session = factory.openSession()) {
                List<Object[]> results = session
                        .createQuery(
                                "SELECT TYPE(n), COUNT(n) FROM Notification n GROUP BY TYPE(n)",
                                Object[].class
                        )
                        .getResultList();

                System.out.println("   Распределение уведомлений:");
                for (Object[] row : results) {
                    Class<?> type = (Class<?>) row[0];
                    Long count = (Long) row[1];
                    System.out.printf("      %s: %d шт.%n", type.getSimpleName(), count);
                }
            }

            // ===== ВЫВОДЫ ПО СТРУКТУРЕ =====
            System.out.println("\n--- СТРУКТУРА ТАБЛИЦ (TABLE_PER_CLASS) ---");
            System.out.println("   📌 Каждая таблица содержит ВСЕ поля (родительские + свои)");
            System.out.println("   📌 Таблицы:");
            System.out.println("      - notification: id, sent_at, status, subject, body, recipient_email");
            System.out.println("      - sms_notification: id, sent_at, status, phone_number, message_text");
            System.out.println("   📌 Нет таблицы родительского класса (только дочерние)");
            System.out.println("   📌 Нет NULL-полей");
            System.out.println("   📌 Полная изоляция подтипов");

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n✅ Программа завершена");
    }
}
