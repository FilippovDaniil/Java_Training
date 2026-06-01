/**
 * Задача 04 — Модуль 12: static vs instance
 *
 * ЗАДАНИЕ:
 *   В классе Game есть статическое поле totalGames (общее число игр)
 *   и обычное поле score (счёт конкретной игры).
 *   1. В конструкторе увеличивайте totalGames.
 *   2. Добавьте instance-метод addPoints(int p), увеличивающий score.
 *   3. Создайте 2 игры, добавьте им разные очки.
 *   4. Выведите счёт каждой игры и общее число игр.
 *   Убедитесь, что score у объектов разный, а totalGames — общий.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Игра 1, счёт: 50
 *   Игра 2, счёт: 30
 *   Всего игр: 2
 *
 * ПОДСКАЗКА:
 *   totalGames — static (общее), score — обычное (своё у каждого объекта).
 */
public class Task04 {
    public static void main(String[] args) {
        // Создайте 2 игры, добавьте очки, выведите счёт и Game.totalGames
    }
}

class Game {
    static int totalGames = 0;
    int score = 0;

    // TODO: конструктор (totalGames++) и метод addPoints(int p)
}
