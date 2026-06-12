package m13_iterator_visitor.practice.task04;

/**
 * Задача 04 — Тема 13: Visitor — две операции над одной иерархией
 *
 * ЗАДАНИЕ:
 *   Покажите главную выгоду Visitor: новая операция = новый визитор, классы
 *   элементов не меняются. Возьмите мини-дерево арифметики:
 *     - интерфейс ExprVisitor (файл ExprVisitor.java): long visitNum(Num n);
 *       long visitAdd(Add a);
 *     - интерфейс Expr (файл Expr.java): long accept(ExprVisitor v);
 *     - Num (файл Num.java): значение value; accept → v.visitNum(this);
 *     - Add (файл Add.java): left, right (Expr); accept → v.visitAdd(this);
 *     - EvalVisitor (файл EvalVisitor.java): visitNum = value; visitAdd =
 *       left.accept(this) + right.accept(this);
 *     - CountNumVisitor (файл CountNumVisitor.java): считает количество чисел:
 *       visitNum = 1; visitAdd = left.accept(this) + right.accept(this).
 *   В main: соберите выражение (2 + (3 + 4)), посчитайте его значение и число
 *   листьев-чисел двумя визиторами.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Значение: 9
 *   Чисел в выражении: 3
 *
 * ТРЕБОВАНИЯ:
 *   - Num/Add НЕ содержат ни eval, ни count — операции в визиторах;
 *   - добавление второй операции (CountNumVisitor) не потребовало правок Num/Add;
 *   - рекурсия по дереву идёт через accept(this) у дочерних узлов.
 *
 * ПОДСКАЗКА:
 *   Add.accept(v) = v.visitAdd(this); внутри visitAdd визитор сам спускается в
 *   left/right через accept(this).
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: Expr e = new Add(new Num(2), new Add(new Num(3), new Num(4)));
        //       выведите e.accept(new EvalVisitor()) и e.accept(new CountNumVisitor())
    }
}
