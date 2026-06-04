/**
 * Задача 07 — Модуль 118: МИНИ-ПРОЕКТ «Финальный шаблон Task Tracker» — КАПСТОУН КУРСА
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок + println, компилируется bare-javac).
 *
 * ЦЕЛЬ: собрать ПОЛНЫЙ переиспользуемый production-ready шаблон Task Tracker — итог всего курса.
 *       Это финальная задача: соединить Security + Test + Docker в готовый к деплою артефакт.
 *
 * ЗАДАНИЕ — оформите в TEMPLATE ниже структуру проекта и ключевые файлы:
 *
 *   СТРУКТУРА:
 *     ├── Dockerfile            (multi-stage + layered + non-root + JVM-tuning + HEALTHCHECK — задача 01)
 *     ├── .dockerignore         (задача 03)
 *     ├── docker-compose.yml    (app + db, том, healthcheck, лимиты, ${POSTGRES_PASSWORD} — задача 02)
 *     ├── .env.example          (POSTGRES_PASSWORD=change-me — задача 03)
 *     └── src/...               (приложение: REST + JWT security + тесты — модули 67–110)
 *
 *   КОМАНДА РАЗВЁРТЫВАНИЯ (одна):
 *     cp .env.example .env && docker compose up --build -d
 *
 *   ПРОВЕРКА:
 *     curl http://localhost:8080/actuator/health      → UP
 *     POST /api/auth/login → JWT;  GET /api/tasks с Bearer-токеном → 200  (модули 98–99)
 *
 *   ЧЕК-ЛИСТ ПРОЙДЕН (задача 05): образ / конфиг / данные-оркестрация — все пункты [x].
 *
 * ИТОГ КУРСА:
 *   Task Tracker API прошёл путь: REST (67–76) → Data JPA (77–92) → Security/JWT (93–100)
 *   → полное покрытие тестами (101–110) → контейнеризация (111–118).
 *   От Hello World (модуль 01) до production-ready микросервиса в Docker. Поздравляем — курс пройден!
 *
 * ПОДСКАЗКА: этот шаблон копируется в новый Spring Boot проект → сразу грамотная упаковка.
 *            Дальше (за рамками курса): Kubernetes/Helm (rancher.md), CI/CD, мониторинг.
 */
public class Task07 {
    public static void main(String[] args) {
        String template = """
                # TODO: соберите финальный шаблон Task Tracker (итог курса)

                # СТРУКТУРА:
                #   Dockerfile          → задача 01 (multi-stage, non-root, tuning, healthcheck)
                #   .dockerignore       → задача 03
                #   docker-compose.yml  → задача 02 (app+db, том, healthcheck, лимиты)
                #   .env.example        → задача 03 (POSTGRES_PASSWORD=change-me)
                #   src/...             → REST + JWT + тесты (модули 67–110)

                # РАЗВЁРТЫВАНИЕ (одна команда):
                #   cp .env.example .env && docker compose up --build -d

                # ПРОВЕРКА:
                #   curl http://localhost:8080/actuator/health        → UP
                #   POST /api/auth/login → JWT → GET /api/tasks (Bearer) → 200

                # ЧЕК-ЛИСТ (задача 05): образ / конфиг / оркестрация — все пункты [x].

                # ── КУРС 01–118 ЗАВЕРШЁН: Core Java → production-ready Spring Boot микросервис ──
                """;
        System.out.println(template);
    }
}
