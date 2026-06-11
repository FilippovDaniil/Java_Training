package m03_solid_lsp_isp_dip.practice.task02;

/**
 * Задача 02 — Тема 03: LSP — летающие и нелетающие птицы
 *
 * ЗАДАНИЕ:
 *   Если у базового Bird есть метод fly(), а Penguin его «выключает»
 *   (throw UnsupportedOperationException), подстановка пингвина ломает код —
 *   нарушение LSP. Разведите способности по интерфейсам:
 *     - Bird (файл Bird.java): String name(); void eat(); — общее для всех птиц;
 *     - Flyer (файл Flyer.java): void fly(); — ТОЛЬКО для летающих;
 *     - Sparrow реализует Bird и Flyer; Penguin реализует только Bird.
 *   В main: накормите всех птиц; отправьте в полёт ТОЛЬКО тех, кто Flyer.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Воробей ест
 *   Пингвин ест
 *   Воробей летит
 *
 * ТРЕБОВАНИЯ:
 *   - ни один класс не бросает UnsupportedOperationException;
 *   - «полёт» доступен через тип Flyer, и Penguin им просто не является;
 *   - перебирая список, проверяйте способность через тип Flyer
 *     (instanceof Flyer ЗДЕСЬ допустим — это проверка наличия роли, а не
 *      ветвление по конкретному классу).
 *
 * ПОДСКАЗКА:
 *   LSP: подтип не должен «отнимать» обещанное базовым типом. Решение —
 *   не обещать полёт всем птицам, а вынести его в отдельную роль (ISP тоже).
 */

import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        // TODO: List<Bird> с Sparrow и Penguin; eat() у всех;
        //       fly() только у тех, кто instanceof Flyer
    }
}
