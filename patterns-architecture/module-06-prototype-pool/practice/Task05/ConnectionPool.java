import java.util.ArrayDeque;
import java.util.Deque;

class ConnectionPool {
    private final Deque<PooledConnection> free = new ArrayDeque<>();
    // TODO: счётчик nextId для новых соединений (старт с 1)

    public PooledConnection acquire() {
        // TODO: если free пуст — создать new PooledConnection(nextId++);
        //       иначе вернуть free.poll()
        return null;
    }

    public void release(PooledConnection c) {
        // TODO: вернуть соединение в free
    }
}
