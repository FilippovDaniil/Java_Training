package m27_maven.practice;

/**
 * Задача 06 — Модуль 27: Запуск собранного jar (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   1. Убедитесь, что в src/main/java есть класс с методом main,
 *      печатающий что-нибудь в консоль.
 *   2. Соберите проект: mvn package.
 *   3. Запустите получившийся jar напрямую через java -jar.
 *      (Подсказка: для запускаемого jar обычно нужен Main-Class
 *      в манифесте — настройте maven-jar-plugin или укажите класс
 *      явно через classpath.)
 *
 * ОЖИДАЕМЫЕ КОМАНДЫ:
 *   mvn clean package
 *   java -cp target/my-app-1.0.0.jar com.example.Main
 *   (или java -jar target/my-app-1.0.0.jar при настроенном манифесте)
 *
 * ПРОВЕРКА:
 *   в консоли появляется вывод вашего метода main.
 */
public class Task06 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
