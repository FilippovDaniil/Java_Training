package m74_spring_rest_crud_file.practice.task07;

/**
 * Задача 07 — Модуль 74: МИНИ-ПРОЕКТ «Вложения задач Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: реализовать работу с вложениями задачи — загрузка, список, скачивание,
 *       плюс CSV-экспорт всех задач. Хранилище — в памяти.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Хранилище вложений: Map<Long, List<Attachment07>>, где ключ — taskId.
 *   Attachment07 — record (String name, String contentType, byte[] content).
 *
 *   AttachmentController07 (@RequestMapping("/api/tasks")):
 *     1) upload(taskId, file):
 *          POST /api/tasks/{taskId}/attachments (multipart, поле "file")
 *          - сохранить Attachment07 в список задачи
 *          - 201 + "Загружено: <name> к задаче <taskId>"
 *     2) list(taskId):
 *          GET /api/tasks/{taskId}/attachments
 *          - вернуть список ИМЁН вложений (List<String>), пустой → []
 *     3) download(taskId, index):
 *          GET /api/tasks/{taskId}/attachments/{index}
 *          - вернуть содержимое вложения по индексу:
 *            ResponseEntity с правильным contentType и Content-Disposition: attachment
 *          - если задачи/индекса нет → 404
 *     4) exportTasksCsv():
 *          GET /api/tasks/export (produces "text/csv")
 *          - вернуть CSV "taskId,attachmentsCount" по всем задачам, как файл tasks.csv
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP multipart] ──► upload ──► Map<taskId, List<Attachment07>>
 *   [HTTP GET]       ──► list / download / export ◄── (чтение из той же Map)
 *
 * ПОДСКАЗКА:
 *   store.computeIfAbsent(taskId, k -> new ArrayList<>()).add(att);
 *   download: проверьте границы списка перед get(index) → иначе 404.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
