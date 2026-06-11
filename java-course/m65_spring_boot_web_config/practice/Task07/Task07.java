package m65_spring_boot_web_config.practice.task07;

/**
 * Задача 07 — Модуль 65: МИНИ-ПРОЕКТ «REST CRUD-микросервис заметок»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать всё из модуля 65 в один сервис — CRUD, DTO, валидация,
 *       корректные статусы (200/201/204/404), единый контракт ошибок.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) NoteService07 (@Service) — хранилище заметок в памяти (Map<Long, Note>):
 *        - create(text)        — присвоить новый id, сохранить, вернуть Note
 *        - findAll()           — вернуть List<Note>
 *        - findById(id)        — Optional<Note>
 *        - update(id, text)    — обновить текст; если нет — бросить NoSuchElementException
 *        - delete(id)          — удалить; если нет — бросить NoSuchElementException
 *      Note — record (Long id, String text).
 *
 *   2) CreateNoteDto — record (String text) с @NotBlank @Size(max = 280).
 *
 *   3) NoteController07 (@RestController, @RequestMapping("/api/notes")):
 *        - POST   /api/notes        @Valid @RequestBody CreateNoteDto → 201 + созданная заметка
 *        - GET    /api/notes        → 200 + список
 *        - GET    /api/notes/{id}   → 200 + заметка, либо 404 (через ResponseEntity)
 *        - PUT    /api/notes/{id}   @Valid @RequestBody CreateNoteDto → 200 + обновлённая
 *        - DELETE /api/notes/{id}   → 204 No Content
 *
 *   4) NoteExceptionHandler07 (@RestControllerAdvice):
 *        - MethodArgumentNotValidException → 400 + { status, error, fields }
 *        - NoSuchElementException          → 404 + { status, error }
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP] ──► NoteController07 ──► NoteService07 (Map<Long, Note>)
 *                    │
 *           NoteExceptionHandler07 (единый формат ошибок)
 *
 * ПОДСКАЗКА:
 *   id-генератор: private final AtomicLong seq = new AtomicLong(1);
 *   204: ResponseEntity.noContent().build();
 *   404: ResponseEntity.notFound().build();
 */

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ConcurrentHashMap;

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
