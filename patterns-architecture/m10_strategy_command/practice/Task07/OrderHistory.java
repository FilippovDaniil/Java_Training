package m10_strategy_command.practice.task07;

import java.util.ArrayDeque;
import java.util.Deque;

class OrderHistory {
    private final Deque<OrderCommand> stack = new ArrayDeque<>();

    public void run(OrderCommand command) {
        // TODO: command.execute(); положить в stack
    }

    public void undoLast() {
        // TODO: если стек не пуст — снять и вызвать undo()
    }
}
