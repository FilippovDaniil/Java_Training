package m73_spring_rest_collections.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.IntStream;

record PagedResponse05<T>(List<T> content, int page, int size,
                          long totalElements, int totalPages, boolean hasNext) {}
