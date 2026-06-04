/**
 * Задача 01 — Модуль 114: конфигурация через переменные окружения (-e)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Запустите ОДИН образ tasktracker:1.0.0 с конфигурацией, переданной снаружи через -e:
 *   1) docker run -d -p 8080:8080 \
 *          -e SPRING_PROFILES_ACTIVE=prod \
 *          -e SERVER_PORT=8080 \
 *          -e APP_MAX_TASKS=100 \
 *          tasktracker:1.0.0
 *   2) Проверьте, что значения применились (через эндпоинт/лог приложения).
 *   3) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: усвоить relaxed binding — свойство app.max-tasks задаётся переменной APP_MAX_TASKS.
 *
 * ТАБЛИЦА СООТВЕТСТВИЙ:
 *   spring.profiles.active → SPRING_PROFILES_ACTIVE
 *   server.port           → SERVER_PORT
 *   app.max-tasks         → APP_MAX_TASKS
 *
 * ПОДСКАЗКА: точки и дефисы в имени свойства → подчёркивания и верхний регистр в env.
 */
public class Task01 {
    public static void main(String[] args) {
        String commands = """
                # TODO: запуск с конфигурацией через -e
                # docker run -d -p 8080:8080 \\
                #     -e SPRING_PROFILES_ACTIVE=prod \\
                #     -e SERVER_PORT=8080 \\
                #     -e APP_MAX_TASKS=100 \\
                #     tasktracker:1.0.0
                """;
        System.out.println(commands);
    }
}
