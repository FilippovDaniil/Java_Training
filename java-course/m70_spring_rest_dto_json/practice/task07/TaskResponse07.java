package m70_spring_rest_dto_json.practice.task07;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TaskResponse07(
        Long id,
        String title,
        String status,
        AssigneeDto07 assignee,
        @JsonFormat(pattern="dd.MM.yyyy HH:mm")
        LocalDateTime createdAt
) {
}
