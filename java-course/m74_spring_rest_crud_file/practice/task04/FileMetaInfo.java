package m74_spring_rest_crud_file.practice.task04;

import java.time.Instant;

public record FileMetaInfo(String title, String author, long size, String contentType, Instant uploadedAt) {
}
