package m62_spring_core_configuration.practice.task06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// ============================================================
// Компонент, читающий ресурсы
// ============================================================

// TODO: @Component
@Component
public class ResourceReader {

    // Используем file: с относительным путём от корня проекта
    @Value("file:java-course/m62_spring_core_configuration/practice/task06/resources/banner.txt")
    private Resource bannerResource;

    @Value("file:java-course/m62_spring_core_configuration/practice/task06/resources/application.properties")
    private Resource propsResource;

    public void readResources() throws IOException {
        // 1. Читаем баннер
        String banner = new String(bannerResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("=== BANNER ===\n" + banner);

        // 2. Выводим имя и размер файла свойств
        System.out.println("=== СВОЙСТВА ===");
        System.out.println("Файл: " + propsResource.getFilename()
                + ", байт: " + propsResource.contentLength());

        // 3. Проверяем FileSystemResource программно (как в задании)
        FileSystemResource fsr = new FileSystemResource(
                "java-course/m62_spring_core_configuration/practice/task06/resources/banner.txt"
        );
        System.out.println("=== ПРОВЕРКА FileSystemResource ===");
        System.out.println("Существует: " + fsr.exists());
        if (fsr.exists()) {
            System.out.println("Абсолютный путь: " + fsr.getFile().getAbsolutePath());
        }
    }
}
