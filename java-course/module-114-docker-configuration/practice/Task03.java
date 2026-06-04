/**
 * Задача 03 — Модуль 114: --env-file (конфигурация пачкой из файла)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с .env + команды + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Вынесите набор переменных в файл и подайте его одним флагом:
 *   1) Создайте файл .env.prod (НЕ коммитить — добавьте в .gitignore):
 *        SPRING_PROFILES_ACTIVE=prod
 *        SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/tasktracker
 *        SPRING_DATASOURCE_USERNAME=app
 *        SPRING_DATASOURCE_PASSWORD=secret
 *   2) Запустите с файлом окружения:
 *        docker run -d -p 8080:8080 --env-file .env.prod tasktracker:1.0.0
 *   3) Добавьте .env* в .gitignore.
 *   4) Впишите содержимое .env.prod и команды в CONFIG ниже.
 *
 * ЦЕЛЬ: управлять конфигурацией пачкой через --env-file (удобно для многих переменных).
 *
 * ВАЖНО: файлы .env с секретами НЕ попадают в git (.gitignore) и НЕ в образ.
 *
 * ПОДСКАЗКА: формат .env — строки KEY=value без кавычек и пробелов вокруг =.
 */
public class Task03 {
    public static void main(String[] args) {
        String config = """
                # Файл: .env.prod (gitignored!)
                # TODO: перечислите переменные
                # SPRING_PROFILES_ACTIVE=prod
                # SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/tasktracker
                # SPRING_DATASOURCE_USERNAME=app
                # SPRING_DATASOURCE_PASSWORD=secret
                #
                # docker run -d -p 8080:8080 --env-file .env.prod tasktracker:1.0.0
                #
                # .gitignore:
                #   .env*
                """;
        System.out.println(config);
    }
}
