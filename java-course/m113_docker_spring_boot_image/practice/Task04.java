package m113_docker_spring_boot_image.practice;

/**
 * Задача 04 — Модуль 113: настройка bootBuildImage в build.gradle
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с фрагментом build.gradle + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Закрепите имя образа и параметры Buildpacks в build.gradle, чтобы не передавать флаги каждый раз:
 *   1) В build.gradle добавьте конфигурацию задачи bootBuildImage:
 *        tasks.named('bootBuildImage') {
 *            imageName = 'tasktracker:1.0.0'
 *            environment = ['BP_JVM_VERSION': '17']
 *        }
 *   2) Теперь достаточно: ./gradlew bootBuildImage
 *   3) Впишите фрагмент в GRADLE ниже.
 *
 * ЦЕЛЬ: задать имя/версию JVM для Buildpacks декларативно в сборке (повторяемость).
 *
 * ВАЖНО: BP_JVM_VERSION управляет версией JRE в образе; imageName — тег результата.
 *
 * ПОДСКАЗКА: после настройки команда сборки упрощается до ./gradlew bootBuildImage без флагов.
 */
public class Task04 {
    public static void main(String[] args) {
        String gradle = """
                // Файл: build.gradle (фрагмент)
                // TODO: настройте задачу bootBuildImage
                // tasks.named('bootBuildImage') {
                //     imageName = 'tasktracker:1.0.0'
                //     environment = ['BP_JVM_VERSION': '17']
                // }
                //
                // Сборка: ./gradlew bootBuildImage
                """;
        System.out.println(gradle);
    }
}
