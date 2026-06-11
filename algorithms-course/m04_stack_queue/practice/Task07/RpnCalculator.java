package m04_stack_queue.practice.task07;

/**
 * Модуль Data-Cruncher: вычисление выражений в обратной польской записи (RPN).
 * RPN не нуждается в скобках: "3 4 + 2 *" = (3+4)*2 = 14. Вычисляется стеком:
 * число -> push; оператор -> снять два операнда, применить, push результат.
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class RpnCalculator {

    /** Вычислить RPN-выражение, токены разделены пробелами. */
    public long eval(String expression) {
        Deque<Long> stack = new ArrayDeque<>();
        // TODO: split(" "); для числа — push; для + - * / — снять b, потом a, посчитать a?b, push
        //       в конце на стеке ровно один элемент — ответ
        return 0;
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}
