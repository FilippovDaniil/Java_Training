package m118_docker_final_template.practice;

/**
 * Задача 04 — Модуль 118: запуск и проверка финального стека «с нуля»
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Проверьте, что стек поднимается «с чистого листа» одной командой (воспроизводимость):
 *   1) Подготовка: cp .env.example .env  (заполнить POSTGRES_PASSWORD).
 *   2) Поднять весь стек: docker compose up --build -d
 *   3) Дождаться готовности и проверить: docker compose ps   (app — healthy)
 *        curl http://localhost:8080/actuator/health    → {"status":"UP"}
 *   4) Логи: docker compose logs -f app
 *   5) Полный сброс и повторный запуск (проверка воспроизводимости):
 *        docker compose down -v ; docker compose up --build -d
 *   6) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: убедиться, что весь стек разворачивается воспроизводимо одной командой.
 *
 * ВАЖНО: «с нуля» = после down -v (тома удалены); если стек поднимается без ручных шагов — шаблон готов.
 *
 * ПОДСКАЗКА: воспроизводимость «с чистого листа» — ключевой признак production-ready шаблона.
 */
public class Task04 {
    public static void main(String[] args) {
        String commands = """
                # TODO: проверка стека «с нуля»
                # cp .env.example .env        # заполнить POSTGRES_PASSWORD
                # docker compose up --build -d
                # docker compose ps           # app → healthy
                # curl http://localhost:8080/actuator/health   # {"status":"UP"}
                # docker compose logs -f app
                #
                # Проверка воспроизводимости:
                # docker compose down -v
                # docker compose up --build -d
                """;
        System.out.println(commands);
    }
}
