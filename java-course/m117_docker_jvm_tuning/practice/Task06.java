package m117_docker_jvm_tuning.practice;

/**
 * Задача 06 — Модуль 117: HEALTHCHECK в Dockerfile (через Actuator)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с Dockerfile + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Встройте проверку здоровья в сам образ (опирается на Spring Boot Actuator):
 *   1) Убедитесь, что в приложении подключён actuator и доступен /actuator/health
 *      (зависимость spring-boot-starter-actuator, эндпоинт открыт).
 *   2) В Dockerfile добавьте:
 *        HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
 *            CMD wget -qO- http://localhost:8080/actuator/health || exit 1
 *   3) Соберите и запустите; статус здоровья виден в docker ps (healthy/unhealthy).
 *   4) Впишите фрагмент Dockerfile в DOCKERFILE ниже.
 *
 * ЦЕЛЬ: образ сам сообщает о здоровье — оркестратор/Compose видят healthy/unhealthy.
 *
 * ВАЖНО: HEALTHCHECK в Dockerfile = встроен в образ; healthcheck в Compose = снаружи (модуль 115). Это разные места.
 *
 * ПОДСКАЗКА: команда возвращает 0 (healthy) или 1 (unhealthy); wget/curl должны быть в образе.
 */
public class Task06 {
    public static void main(String[] args) {
        String dockerfile = """
                # TODO: добавьте HEALTHCHECK в Dockerfile
                # ... (FROM/COPY/ENTRYPOINT как обычно) ...
                # HEALTHCHECK --interval=30s --timeout=3s --retries=3 \\
                #     CMD wget -qO- http://localhost:8080/actuator/health || exit 1
                #
                # docker ps  → STATUS покажет (healthy) / (unhealthy)
                """;
        System.out.println(dockerfile);
    }
}
