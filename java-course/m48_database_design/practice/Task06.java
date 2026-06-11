package m48_database_design.practice;

/**
 * Задача 06 — Модуль 48: Связь «многие ко многим»
 *
 * ЗАДАНИЕ (проектирование):
 *   Спроектируйте связь M:N между студентами и курсами:
 *     - студент может записаться на много курсов;
 *     - на курс записано много студентов.
 *   1) объясните, почему нельзя реализовать M:N одним внешним ключом;
 *   2) создайте связующую (junction) таблицу enrollments
 *      со ссылками на обе таблицы и составным первичным ключом;
 *   3) добавьте в enrollments дополнительный атрибут связи
 *      (например, дату записи или оценку);
 *   4) напишите CREATE TABLE для students, courses и enrollments,
 *      затем пример INSERT (запись студента на курс).
 *
 * ПОДСКАЗКА:
 *   CREATE TABLE enrollments (
 *       student_id INT,
 *       course_id INT,
 *       enrolled_at DATE,
 *       PRIMARY KEY (student_id, course_id),
 *       FOREIGN KEY (student_id) REFERENCES students(id),
 *       FOREIGN KEY (course_id) REFERENCES courses(id)
 *   );
 */
public class Task06 {
    public static void main(String[] args) {
        String sql = """
            -- Спроектируйте M:N через связующую таблицу enrollments
            """;
        System.out.println(sql);
    }
}
