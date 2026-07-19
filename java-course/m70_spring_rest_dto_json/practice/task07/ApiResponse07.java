package m70_spring_rest_dto_json.practice.task07;

import java.time.Instant;

public record ApiResponse07<T>(T data, String message, Instant timestamp) {
    public static <T> ApiResponse07<T> ok(T data) {
        return new ApiResponse07<>(data, "OK", Instant.now());
    }
}
