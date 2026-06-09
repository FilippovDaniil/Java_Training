import java.util.ArrayDeque;
import java.util.Deque;

// Очередь точка-точка (FIFO).
class MessageQueue {
    private final Deque<String> queue = new ArrayDeque<>();

    public void send(String msg) {
        // TODO: положить в конец очереди
    }

    public String receive() {
        // TODO: забрать из начала очереди (poll)
        return null;
    }

    public boolean isEmpty() {
        // TODO: пуста ли очередь
        return true;
    }
}
