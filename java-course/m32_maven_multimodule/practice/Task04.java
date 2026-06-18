package m32_maven_multimodule.practice;

/**
 * Задача 04 — Модуль 32: dependencyManagement (упражнение)
 *
 * ЗАДАНИЕ:
 *   Централизуйте версии зависимостей в родительском POM.
 *     1) в parent pom.xml добавьте секцию <dependencyManagement> с
 *        зависимостью gson указанной версии;
 *     2) в модуле, где нужен gson, объявите его зависимость БЕЗ версии;
 *     3) убедитесь, что версия подтянулась из родителя.
 *
 * ПРОВЕРКА:
 *   mvn dependency:tree в нужном модуле показывает gson с версией
 *   из dependencyManagement (хотя в самом модуле версия не указана).
 *
 * ВОПРОС НА ПОНИМАНИЕ (ответьте в комментарии):
 *   Чем dependencyManagement отличается от обычного <dependencies>
 *   в родителе?
 */
public class Task04 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
        System.out.println("Done");
    }
}
