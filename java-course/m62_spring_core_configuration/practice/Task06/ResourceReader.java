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
class ResourceReader {

    // TODO: @Value("classpath:banner.txt")
    private Resource bannerResource;

    // TODO: @Value("classpath:app.properties")
    private Resource propsResource;

    public void readResources() throws IOException {
        // TODO: 1) прочитать содержимое bannerResource и вывести текст
        //       2) вывести имя файла propsResource и его размер в байтах
        //       3) создать FileSystemResource программно, проверить exists()

        // Пример для шага 1:
        // String banner = new String(bannerResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        // System.out.println("=== BANNER ===\n" + banner);

        // Пример для шага 2:
        // System.out.println("Файл: " + propsResource.getFilename()
        //     + ", байт: " + propsResource.contentLength());

        // Пример для шага 3:
        // FileSystemResource fsr = new FileSystemResource("src/main/resources/banner.txt");
        // System.out.println("Существует: " + fsr.exists() + ", путь: " + fsr.getFile().getAbsolutePath());
    }
}
