/**
 * Задача 03 — Модуль 27: Добавление зависимости (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   1. Добавьте в pom.xml зависимость на библиотеку Gson (для работы
 *      с JSON): com.google.code.gson : gson : 2.10.1.
 *   2. Создайте в src/main/java класс Main, который использует Gson
 *      (например, сериализует объект в JSON-строку).
 *   3. Соберите проект, чтобы Maven скачал зависимость.
 *
 * ФРАГМЕНТ pom.xml:
 *   <dependencies>
 *       <dependency>
 *           <groupId>com.google.code.gson</groupId>
 *           <artifactId>gson</artifactId>
 *           <version>2.10.1</version>
 *       </dependency>
 *   </dependencies>
 *
 * ОЖИДАЕМЫЕ КОМАНДЫ:
 *   mvn compile
 *   mvn dependency:tree
 *
 * ПРОВЕРКА:
 *   dependency:tree показывает gson;
 *   класс с импортом com.google.gson.Gson компилируется.
 */
public class Task03 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
