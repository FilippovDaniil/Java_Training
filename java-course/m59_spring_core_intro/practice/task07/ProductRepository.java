package m59_spring_core_intro.practice.task07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================

/** Базовый интерфейс: список товаров и проверка наличия. */
interface ProductRepository {
    List<String> findAll();
    boolean exists(String name);
}
