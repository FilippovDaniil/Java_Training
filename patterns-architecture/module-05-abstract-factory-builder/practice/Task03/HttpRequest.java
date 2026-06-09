import java.util.LinkedHashMap;
import java.util.Map;

class HttpRequest {
    // TODO: неизменяемые поля method, url, timeoutMs, body, headers (Map)

    private HttpRequest(Builder b) {
        // TODO: скопировать поля; заголовки — в неизменяемую копию
    }

    // TODO: describe()/toString() для вывода

    static class Builder {
        // TODO: поля method, url; timeoutMs=1000; body=""; headers = new LinkedHashMap<>()

        public Builder method(String method) {
            // TODO
            return this;
        }

        public Builder url(String url) {
            // TODO
            return this;
        }

        public Builder timeoutMs(int timeoutMs) {
            // TODO
            return this;
        }

        public Builder body(String body) {
            // TODO
            return this;
        }

        public Builder header(String name, String value) {
            // TODO: положить в headers (накопление), вернуть this
            return this;
        }

        public HttpRequest build() {
            // TODO: проверить method и url, вернуть new HttpRequest(this)
            return new HttpRequest(this);
        }
    }
}
