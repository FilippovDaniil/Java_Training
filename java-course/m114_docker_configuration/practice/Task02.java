package m114_docker_configuration.practice;

/**
 * Задача 02 — Модуль 114: профили Spring в контейнере (SPRING_PROFILES_ACTIVE)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с application.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Один образ — разное поведение по профилю. Подготовьте профиль-специфичные файлы (в образе)
 *   и активируйте профиль снаружи (env):
 *   1) src/main/resources/application.yml (общее):
 *        app:
 *          name: TaskTracker
 *   2) src/main/resources/application-prod.yml:
 *        logging:
 *          level:
 *            root: WARN
 *   3) src/main/resources/application-dev.yml:
 *        logging:
 *          level:
 *            root: DEBUG
 *   4) Запуск с выбором профиля:
 *        docker run -e SPRING_PROFILES_ACTIVE=prod tasktracker:1.0.0
 *        docker run -e SPRING_PROFILES_ACTIVE=dev  tasktracker:1.0.0
 *   5) Впишите YAML и команды в CONFIG ниже.
 *
 * ЦЕЛЬ: профиль-файлы — ВНУТРИ образа, выбор активного профиля — СНАРУЖИ (env).
 *
 * ВАЖНО: секреты в профиль-файлы образа НЕ кладут — только через env/secret-store.
 *
 * ПОДСКАЗКА: application-{profile}.yml активируется при SPRING_PROFILES_ACTIVE={profile}.
 */
public class Task02 {
    public static void main(String[] args) {
        String config = """
                # TODO: профиль-файлы (в образе) + выбор профиля (env)
                # application.yml:
                #   app:
                #     name: TaskTracker
                # application-prod.yml:
                #   logging: { level: { root: WARN } }
                # application-dev.yml:
                #   logging: { level: { root: DEBUG } }
                #
                # docker run -e SPRING_PROFILES_ACTIVE=prod tasktracker:1.0.0
                # docker run -e SPRING_PROFILES_ACTIVE=dev  tasktracker:1.0.0
                """;
        System.out.println(config);
    }
}
