/**
 * Задача 06 — Модуль 62: ресурсы через Resource и @Value("classpath:...")
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Абстракция Resource позволяет читать файлы независимо от их расположения:
 *   1) Создайте файл src/main/resources/banner.txt с произвольным ASCII-текстом.
 *   2) Внедрите его как Resource через @Value("classpath:banner.txt").
 *   3) Прочитайте содержимое: bannerResource.getInputStream().readAllBytes().
 *      Преобразуйте в строку (UTF-8) и выведите в консоль.
 *   4) Добавьте второй Resource — "classpath:app.properties" (файл из Task01).
 *      Выведите: filename, URL, длина в байтах (resource.contentLength()).
 *   5) Попробуйте FileSystemResource программно: new FileSystemResource("путь/к/файлу").
 *      Проверьте resource.exists() и выведите абсолютный путь.
 *
 * АБСТРАКЦИЯ Resource:
 *   | Реализация          | Создаётся через                    |
 *   |---------------------|------------------------------------|
 *   | ClassPathResource   | @Value("classpath:file.txt")       |
 *   | FileSystemResource  | new FileSystemResource("path")     |
 *   | UrlResource         | @Value("https://example.com/x")    |
 *   | ByteArrayResource   | new ByteArrayResource(bytes)       |
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   === BANNER ===
 *   <содержимое banner.txt>
 *   === СВОЙСТВА ===
 *   Файл: app.properties, байт: <размер>
 *
 * ПОДСКАЗКА:
 *   @Value("classpath:banner.txt")
 *   private Resource bannerResource;
 *
 *   String text = new String(bannerResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
 *
 * ПРИМЕР banner.txt:
 *   # src/main/resources/banner.txt
 *   ╔══════════════════════════╗
 *   ║   MyService запущен!     ║
 *   ╚══════════════════════════╝
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Task06 {

    public static void main(String[] args) {
        // TODO: создайте контекст с ResourceConfig.class
        //       получите бин ResourceReader и вызовите readResources()
    }
}

// ============================================================
// Конфигурация
// ============================================================

// TODO: @Configuration, @ComponentScan
class ResourceConfig {
}

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
