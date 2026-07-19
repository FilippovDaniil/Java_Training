package m70_spring_rest_dto_json.practice.task06;

import java.util.List;

public record TaskDetailDto06(Long id, String title, AssigneeDto06 assignee, List<String> tags) {
}
