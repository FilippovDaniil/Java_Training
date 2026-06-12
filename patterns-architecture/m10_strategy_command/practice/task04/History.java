package m10_strategy_command.practice.task04;

import java.util.ArrayDeque;
import java.util.Deque;

// Вызывающий с историей: позволяет отменять команды.
class History {
    private final Deque<Command> stack = new ArrayDeque<>();

    public void run(Command command) {
        // TODO: command.execute(); затем положить в stack
    }

    public void undoLast() {
        // TODO: если стек не пуст — снять команду и вызвать undo()
    }
}
