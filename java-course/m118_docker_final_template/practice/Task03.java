package m118_docker_final_template.practice;

/**
 * Задача 03 — Модуль 118: .dockerignore и .env.example (сопутствующие файлы шаблона)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Добавьте к шаблону сопутствующие файлы:
 *   1) .dockerignore (чистый контекст сборки):
 *        .git
 *        .idea
 *        .gradle
 *        build
 *        *.md
 *   2) .env.example (КОММИТИТСЯ — образец без секретов):
 *        POSTGRES_PASSWORD=change-me
 *   3) .env (gitignored — реальные значения): копируется из .env.example.
 *   4) В .gitignore добавьте: .env
 *   Впишите оба файла в FILES ниже.
 *
 * ЦЕЛЬ: оформить инфраструктуру шаблона — образец переменных в git, реальные секреты вне git.
 *
 * ВАЖНО: .env.example (с плейсхолдерами) — в репозитории; .env (с реальными паролями) — в .gitignore.
 *        Новый разработчик: cp .env.example .env и заполняет значения.
 *
 * ПОДСКАЗКА: .dockerignore ускоряет сборку и не пускает .git/build в образ.
 */
public class Task03 {
    public static void main(String[] args) {
        String files = """
                # Файл: .dockerignore
                # TODO:
                #   .git
                #   .idea
                #   .gradle
                #   build
                #   *.md
                #
                # Файл: .env.example (в git, образец)
                # TODO:
                #   POSTGRES_PASSWORD=change-me
                #
                # Файл: .gitignore — добавить строку:
                #   .env
                #
                # Новый разработчик: cp .env.example .env  → заполнить реальные значения
                """;
        System.out.println(files);
    }
}
