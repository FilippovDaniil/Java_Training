package m06_prototype_pool.practice.task07;

import java.util.ArrayDeque;
import java.util.Deque;

class GatewayPool {
    private final Deque<GatewayConnection> free = new ArrayDeque<>();
    // TODO: счётчик nextId (старт с 1)

    public GatewayConnection acquire() {
        // TODO: free пуст → new GatewayConnection(nextId++); иначе free.poll()
        return null;
    }

    public void release(GatewayConnection c) {
        // TODO: вернуть в free
    }
}
