/**
 * Задача 02 — Модуль 32: Дочерние модули (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   Добавьте к проекту my-platform два дочерних модуля: common и app.
 *     1) создайте подпапки common/ и app/, в каждой — свой pom.xml
 *        с секцией <parent>, ссылающейся на my-platform;
 *     2) в родительском pom.xml перечислите их в <modules>;
 *     3) в каждом модуле создайте src/main/java с простым классом.
 *
 * ФРАГМЕНТЫ:
 *   parent pom.xml:
 *     <modules>
 *         <module>common</module>
 *         <module>app</module>
 *     </modules>
 *   child pom.xml:
 *     <parent>
 *         <groupId>com.example</groupId>
 *         <artifactId>my-platform</artifactId>
 *         <version>1.0.0</version>
 *     </parent>
 *     <artifactId>common</artifactId>
 *
 * ПРОВЕРКА:
 *   mvn compile в корне собирает оба модуля (видно в логе реактора).
 */
public class Task02 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
