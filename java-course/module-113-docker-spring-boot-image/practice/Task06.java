/**
 * Задача 06 — Модуль 113: настройка layered jars в build.gradle (опционально)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с фрагментом build.gradle + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Layered jars включены по умолчанию, но слои можно настраивать. Ознакомьтесь с конфигурацией:
 *   1) В build.gradle (по умолчанию расслоение уже активно):
 *        bootJar {
 *            layered {
 *                enabled = true     // включено по умолчанию в Spring Boot 2.4+
 *            }
 *        }
 *   2) Проверьте слои собранного jar: java -Djarmode=layertools -jar build/libs/app.jar list
 *   3) Впишите фрагмент в GRADLE ниже.
 *
 * ЦЕЛЬ: знать, что расслоением можно управлять в сборке (хотя дефолт обычно подходит).
 *
 * ВАЖНО: в большинстве проектов трогать настройку не нужно — дефолтное расслоение оптимально.
 *
 * ПОДСКАЗКА: кастомизация слоёв (layered { application {...} dependencies {...} }) нужна редко.
 */
public class Task06 {
    public static void main(String[] args) {
        String gradle = """
                // Файл: build.gradle (фрагмент, обычно НЕ требуется — дефолт включён)
                // TODO: при необходимости настройте расслоение
                // bootJar {
                //     layered {
                //         enabled = true   // по умолчанию в Spring Boot 2.4+
                //     }
                // }
                //
                // Проверка слоёв:
                // java -Djarmode=layertools -jar build/libs/app.jar list
                """;
        System.out.println(gradle);
    }
}
