package m07_multidimensional_arrays.practice;

import java.util.Scanner;

/**
 * Задача 07 — Модуль 07 (МИНИ-ПРОЕКТ): Зал кинотеатра
 *
 * ЗАДАНИЕ:
 *   Смоделируйте схему зала 5 рядов x 8 мест двумерным массивом int,
 *   где 0 — свободно, 1 — занято. Часть мест уже занята (см. ниже).
 *   Реализуйте:
 *     1) вывод карты зала: свободное место как ".", занятое как "X";
 *     2) подсчёт свободных и занятых мест;
 *     3) «бронирование» места по введённым ряду и месту:
 *        - если место свободно — пометить занятым и вывести "Забронировано";
 *        - если занято — вывести "Место уже занято";
 *        - если координаты вне зала — вывести "Неверные координаты".
 *   В конце снова выведите карту зала.
 *
 * ПРИМЕР КАРТЫ:
 *   . . X . . . . .
 *   . . . . X X . .
 *   ...
 *
 * ПОДСКАЗКА:
 *   Перед обращением hall[row][seat] проверьте границы:
 *   row в диапазоне 0..hall.length-1, seat — 0..hall[0].length-1.
 *   Можно нумеровать ряды/места с 1 для пользователя и вычитать 1.
 */

public class Task07 {
    public static void main(String[] args) {
        int[][] hall = new int[5][8];
        // предзаполнение нескольких занятых мест
        hall[0][2] = 1;
        hall[1][4] = 1;
        hall[1][5] = 1;
        hall[3][0] = 1;
        hall[4][7] = 1;

        Scanner scanner = new Scanner(System.in);

        // 1. Вывод начальной карты зала
        System.out.println("Схема зала (начальная):");
        printHall(hall);
        printStats(hall);

        // 2. Бронирование места
        System.out.print("\nВведите номер ряда (1-5): ");
        int row = scanner.nextInt();
        System.out.print("Введите номер места (1-8): ");
        int seat = scanner.nextInt();

        // Преобразуем в индексы массива (пользователь вводит с 1)
        int rowIndex = row - 1;
        int seatIndex = seat - 1;

        // Проверка корректности координат
        if (rowIndex < 0 || rowIndex >= hall.length ||
                seatIndex < 0 || seatIndex >= hall[0].length) {
            System.out.println("Неверные координаты!");
        } else if (hall[rowIndex][seatIndex] == 1) {
            System.out.println("Место уже занято.");
        } else {
            hall[rowIndex][seatIndex] = 1;
            System.out.println("Забронировано!");
        }

        // 3. Вывод обновлённой карты и статистики
        System.out.println("\nСхема зала (после бронирования):");
        printHall(hall);
        printStats(hall);

        scanner.close();
    }

    /**
     * Выводит карту зала в консоль.
     * '.' — свободное место, 'X' — занятое.
     * @param hall двумерный массив (ряды x места)
     */
    public static void printHall(int[][] hall) {
        for (int i = 0; i < hall.length; i++) {
            // Печатаем номер ряда (для удобства, начиная с 1)
            System.out.print((i + 1) + ": ");
            for (int j = 0; j < hall[i].length; j++) {
                System.out.print((hall[i][j] == 0 ? "." : "X") + " ");
            }
            System.out.println();
        }
    }

    /**
     * Подсчитывает и выводит количество свободных и занятых мест.
     * @param hall двумерный массив зала
     */
    public static void printStats(int[][] hall) {
        int free = 0;
        int occupied = 0;
        for (int[] row : hall) {
            for (int seat : row) {
                if (seat == 0) free++;
                else occupied++;
            }
        }
        System.out.println("Свободных мест: " + free);
        System.out.println("Занятых мест: " + occupied);
    }
}