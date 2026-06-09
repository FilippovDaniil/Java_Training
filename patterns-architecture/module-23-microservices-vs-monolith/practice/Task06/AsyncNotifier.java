import java.util.ArrayDeque;
import java.util.Queue;

// Асинхронная доставка: отправка кладёт в очередь, доставка — позже (process).
class AsyncNotifier {
    private final Queue<String> queue = new ArrayDeque<>();

    public void send(String msg) {
        // TODO: добавить msg в очередь; напечатать "[async] в очередь: " + msg
    }

    public void process() {
        // TODO: пока очередь не пуста — извлекать и печатать "[async] доставлено: " + msg
    }
}
