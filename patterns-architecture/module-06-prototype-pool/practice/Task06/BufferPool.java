import java.util.ArrayDeque;
import java.util.Deque;

class BufferPool {
    private final Deque<Buffer> free = new ArrayDeque<>();
    private final int maxSize;
    private int created = 0;

    public BufferPool(int maxSize) {
        // TODO: задать maxSize
        this.maxSize = maxSize;
    }

    public Buffer acquire() {
        // TODO: если есть свободный — вернуть его;
        //       иначе если created < maxSize — создать новый (created++);
        //       иначе бросить IllegalStateException("пул исчерпан")
        return null;
    }

    public void release(Buffer b) {
        // TODO: b.reset(); вернуть в free
    }

    public int createdCount() {
        // TODO: вернуть created
        return 0;
    }
}
