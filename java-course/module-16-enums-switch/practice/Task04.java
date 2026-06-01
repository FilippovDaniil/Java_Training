/**
 * Задача 04 — Модуль 16: Метод внутри enum
 *
 * ЗАДАНИЕ:
 *   Дан enum Operation (PLUS, MINUS, MULTIPLY, DIVIDE). Добавьте ему
 *   метод apply(int a, int b), который выполняет соответствующее
 *   действие (используйте switch внутри метода). В main примените
 *   все операции к числам 12 и 4.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   PLUS: 16
 *   MINUS: 8
 *   MULTIPLY: 48
 *   DIVIDE: 3
 *
 * ПОДСКАЗКА:
 *   int apply(int a, int b) { switch(this) { case PLUS: return a+b; ... } }
 *   Внутри метода enum доступно ключевое слово this — текущая константа.
 */
public class Task04 {
    public static void main(String[] args) {
        // Примените каждую операцию к 12 и 4
    }
}

enum Operation {
    PLUS, MINUS, MULTIPLY, DIVIDE;

    // TODO: метод int apply(int a, int b)
}
