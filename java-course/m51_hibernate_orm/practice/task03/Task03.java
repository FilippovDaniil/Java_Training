package m51_hibernate_orm.practice.task03;

/**
 * Задача 03 — Модуль 51: Поиск сущности по id (find / get)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Создайте SessionFactory и сохраните несколько постов (id присвоятся автоматически).
 *   2) Загрузите пост по известному id с помощью session.get(Post3.class, id).
 *   3) Выведите title и content найденного поста.
 *   4) Попробуйте найти пост с несуществующим id (например, 999L).
 *      Убедитесь, что метод get() возвращает null (а не бросает исключение).
 *
 * РАЗНИЦА get() vs load():
 *   session.get(Post3.class, id)   — возвращает null если нет записи
 *   session.load(Post3.class, id)  — возвращает прокси; ObjectNotFoundException при доступе к полям
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: insert into posts …
 *   Найден: id=1, title="Первый пост"
 *   По id=999: null
 *
 * ПОДСКАЗКА:
 *   Post3 found = session.get(Post3.class, 1L);
 *   if (found != null) {
 *       System.out.println("Найден: " + found.getTitle());
 *   }
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import m51_hibernate_orm.practice.task03.Post3;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;

public class Task03 {

    public static void main(String[] args) {
        // TODO 1: создайте SessionFactory (аналогично Task02)
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        config.setProperty("hibernate.show_sql", "true");
        config.addAnnotatedClass(Post3.class);
        SessionFactory factory = config.buildSessionFactory();

        // TODO 2: в отдельной транзакции сохраните 2–3 поста через session.persist()
        Post3 post1 = new Post3("Title1","Content1");
        Post3 post2 = new Post3("Title2","Content2");
        Post3 post3 = new Post3("Title3","Content3");

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.persist(post1);   // INSERT выполнится при commit
            session.persist(post2);   // INSERT выполнится при commit
            session.persist(post3);   // INSERT выполнится при commit

            tx.commit();             // фиксируем транзакцию
        }

        // TODO 3: откройте новую Session и найдите пост с id=1L через session.get()
        //         выведите title и content
        try (Session session2 = factory.openSession()) {
            Transaction tx = session2.beginTransaction();
            Post3 find1 = session2.get(Post3.class,1L);
            System.out.println(find1.getContent() + " " + find1.getTitle());

            tx.commit();             // фиксируем транзакцию
        }

        // TODO 4: попробуйте session.get(Post3.class, 999L) и выведите результат (null)
        try (Session session3 = factory.openSession()) {
            Transaction tx = session3.beginTransaction();
            try{
                Post3 find1 = session3.get(Post3.class,999L);
                System.out.println(find1.getContent() + " " + find1.getTitle());
            }catch (NullPointerException e){
                System.out.println("Null");
            }


            tx.commit();             // фиксируем транзакцию
        }

        // TODO 5: закройте factory
        factory.close();
    }
}
