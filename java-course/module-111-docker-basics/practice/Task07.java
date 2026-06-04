/**
 * Задача 07 — Модуль 111: МИНИ-ПРОЕКТ «CLI-сценарий: поднять и проверить стек Task Tracker»
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЦЕЛЬ: собрать из команд модуля полный сценарий — поднять БД и приложение в контейнерах,
 *       проверить работу, посмотреть логи, корректно остановить и убрать за собой.
 *
 * ЗАДАНИЕ — соберите в SCRIPT ниже последовательность (выполните в терминале и впишите):
 *   1) Поднять PostgreSQL (env: POSTGRES_DB/USER/PASSWORD), порт 5432, имя db.
 *   2) Собрать jar приложения: ./gradlew bootJar.
 *   3) Запустить приложение в eclipse-temurin:17-jre, порт 8080, монтировать jar,
 *      передать через -e координаты БД (SPRING_DATASOURCE_URL и т.д.).
 *      ⚠️ Контейнеры по умолчанию НЕ видят друг друга по localhost — для связи между ними
 *         в этой задаче проще указать host.docker.internal или общий --network (полноценно — Compose, модуль 115).
 *   4) Проверить здоровье: curl http://localhost:8080/actuator/health.
 *   5) Логи приложения: docker logs -f app.
 *   6) Остановить и удалить ВСЁ: docker stop app db ; docker rm app db.
 *
 * ОЖИДАЕМЫЙ ИТОГ: понимание полного ручного цикла «поднять стек → проверить → убрать».
 *   Боль ручного связывания контейнеров мотивирует переход к docker-compose (модуль 115).
 *
 * ПОДСКАЗКА: имена контейнеров (db, app) позволяют адресовать их в командах stop/rm/logs.
 */
public class Task07 {
    public static void main(String[] args) {
        String script = """
                # TODO: соберите полный CLI-сценарий поднятия и проверки стека

                # 1) База данных
                # docker run -d --name db -p 5432:5432 \\
                #     -e POSTGRES_DB=tasktracker -e POSTGRES_USER=app -e POSTGRES_PASSWORD=secret \\
                #     postgres:16-alpine

                # 2) Сборка приложения
                # ./gradlew bootJar

                # 3) Запуск приложения (координаты БД через -e)
                # docker run -d --name app -p 8080:8080 \\
                #     -v "$(pwd)/build/libs/app.jar:/app.jar" \\
                #     -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/tasktracker \\
                #     -e SPRING_DATASOURCE_USERNAME=app \\
                #     -e SPRING_DATASOURCE_PASSWORD=secret \\
                #     eclipse-temurin:17-jre java -jar /app.jar

                # 4) Проверка
                # curl http://localhost:8080/actuator/health

                # 5) Логи
                # docker logs -f app

                # 6) Уборка
                # docker stop app db
                # docker rm app db
                """;
        System.out.println(script);
    }
}
