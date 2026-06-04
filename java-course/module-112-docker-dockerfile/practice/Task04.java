/**
 * Задача 04 — Модуль 112: .dockerignore (чистый контекст сборки)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Исключите лишнее из контекста сборки, чтобы ускорить build и не тащить мусор в образ.
 *   1) Создайте файл .dockerignore в корне проекта со списком исключений:
 *        .git
 *        .idea
 *        build           # если jar собирается внутри образа (иначе НЕ исключайте build/libs)
 *        *.md
 *        .gradle
 *   2) Соберите образ и сравните время/размер контекста (в выводе docker build видна
 *      строка "Sending build context to Docker daemon ...").
 *   3) Впишите содержимое .dockerignore в IGNORE ниже.
 *
 * ЦЕЛЬ: уменьшить контекст сборки — быстрее build, меньше шанс занести лишнее в образ.
 *
 * ВАЖНО: если jar КОПИРУЕТСЯ снаружи (COPY build/libs/app.jar), не исключайте этот путь целиком —
 *        исключайте build, но при необходимости делайте оговорку (!build/libs/*.jar).
 *
 * ПОДСКАЗКА: .dockerignore по синтаксису похож на .gitignore.
 */
public class Task04 {
    public static void main(String[] args) {
        String ignore = """
                # Файл: .dockerignore (в корне проекта)
                # TODO: перечислите исключения
                # .git
                # .idea
                # .gradle
                # *.md
                # build
                # !build/libs/*.jar      # оговорка, если jar копируется снаружи
                """;
        System.out.println(ignore);
    }
}
