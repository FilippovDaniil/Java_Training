import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

class ImageCache {
    private final Map<String, SoftReference<byte[]>> cache = new HashMap<>();

    // TODO: put(String, byte[]); byte[] get(String); int size()
}
