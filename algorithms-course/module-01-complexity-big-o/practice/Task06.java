/**
 * Задача 06 — Тема 01: Сложность по памяти у рекурсивного факториала
 *
 * ЗАДАНИЕ:
 *   1) Реализуйте рекурсивный factorialRec(n) и итеративный factorialIter(n).
 *   2) В TODO-комментариях укажите для КАЖДОГО сложность по ВРЕМЕНИ и по ПАМЯТИ.
 *      Подсказка: рекурсия создаёт n кадров на стеке вызовов -> память O(n);
 *      итерация — O(1).
 *   3) В main продемонстрируйте, что глубокая рекурсия опасна: вызовите
 *      factorialRec для большого n в try/catch и поймайте StackOverflowError.
 *
 * ПРИМЕР / ВЫВОД:
 *   5! = 120 (рекурсия), 120 (итерация)
 *   factorialRec(100000): StackOverflowError — глубина рекурсии = доп. память O(n)
 *
 * ТРЕБОВАНИЯ:
 *   - используйте long (или BigInteger, если хотите точные большие значения);
 *   - StackOverflowError ловится через catch (StackOverflowError e);
 *   - для демонстрации переполнения стека n должно быть большим (десятки/сотни тыс.).
 *
 * ПОДСКАЗКА:
 *   StackOverflowError — это Error, не Exception; ловите его явно.
 */

public class Task06 {

    // TODO: время? ___  память? ___
    static long factorialRec(int n) {
        // TODO: базовый случай n<=1 -> 1; иначе n * factorialRec(n-1)
        return 1;
    }

    // TODO: время? ___  память? ___
    static long factorialIter(int n) {
        // TODO: цикл от 2 до n, накапливать произведение
        return 1;
    }

    public static void main(String[] args) {
        // TODO: вывести 5! двумя способами; затем в try/catch вызвать
        //       factorialRec для большого n и поймать StackOverflowError
    }
}
