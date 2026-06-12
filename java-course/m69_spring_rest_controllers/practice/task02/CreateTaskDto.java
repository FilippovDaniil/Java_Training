package m69_spring_rest_controllers.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

record CreateTaskDto(String title) {}
