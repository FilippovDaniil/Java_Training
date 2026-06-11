package m20_mst_prim_kruskal.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 20: оптимальная сеть связи
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher NetworkBuilder (файл NetworkBuilder.java):
 *   по набору возможных каналов связи (u, v, стоимость) постройте минимальную
 *   по стоимости сеть, объединяющую все узлы (MST). Верните стоимость и
 *   выбранные каналы.
 *
 * ПРИМЕР / ВЫВОД:
 *   узлы 0..3, каналы: (0-1,1),(1-2,2),(2-3,3),(0-3,10),(0-2,4)
 *   минимальная стоимость = 6
 *   выбранные каналы: [0-1(1), 1-2(2), 2-3(3)]
 *
 * ТРЕБОВАНИЯ:
 *   - MST через Крускал + DSU;
 *   - minTotalCost = -1, если узлы нельзя связать;
 *   - chosenLinks возвращает рёбра MST.
 *
 * ПОДСКАЗКА:
 *   Это «боевое» применение MST: минимизируем длину/стоимость соединений сети.
 */

import java.util.Arrays;

public class Task07 {
    public static void main(String[] args) {
        NetworkBuilder nb = new NetworkBuilder(4);
        nb.addLink(0,1,1); nb.addLink(1,2,2); nb.addLink(2,3,3); nb.addLink(0,3,10); nb.addLink(0,2,4);
        System.out.println("стоимость = " + nb.minTotalCost());
        nb.chosenLinks().forEach(e -> System.out.println(Arrays.toString(e)));
    }
}
