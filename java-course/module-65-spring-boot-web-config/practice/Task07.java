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

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// ============================================================
// Доменная модель и DTO
// ============================================================

record Note(Long id, String text) {}

// TODO: @NotBlank @Size(max = 280) на поле text
record CreateNoteDto(/* TODO: валидация */ String text) {}

// ============================================================
// Сервис (каркас)
// ============================================================

// TODO: @Service
class NoteService07 {
    private final Map<Long, Note> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Note create(String text) {
        // TODO: id = seq.getAndIncrement(); создать Note; положить в store; вернуть
        return null;
    }

    public List<Note> findAll() {
        // TODO: верните new ArrayList<>(store.values())
        return null;
    }

    public Optional<Note> findById(Long id) {
        // TODO: Optional.ofNullable(store.get(id))
        return Optional.empty();
    }

    public Note update(Long id, String text) {
        // TODO: если нет — throw new NoSuchElementException("Заметка не найдена: " + id)
        // TODO: создать обновлённую Note, положить, вернуть
        return null;
    }

    public void delete(Long id) {
        // TODO: если store.remove(id) == null — throw new NoSuchElementException(...)
    }
}

// ============================================================
// Контроллер (каркас)
// ============================================================

// TODO: @RestController + @RequestMapping("/api/notes")
class NoteController07 {

    // TODO: внедрите NoteService07 через конструктор

    // TODO: @PostMapping → 201 Created + тело
    public ResponseEntity<Note> create(/* @Valid @RequestBody */ CreateNoteDto dto) {
        return null;
    }

    // TODO: @GetMapping → 200 + список
    public List<Note> all() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    public ResponseEntity<Note> one(/* @PathVariable */ Long id) {
        // TODO: service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build())
        return null;
    }

    // TODO: @PutMapping("/{id}") → 200 + обновлённая
    public Note update(/* @PathVariable */ Long id, /* @Valid @RequestBody */ CreateNoteDto dto) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → 204 No Content
    public ResponseEntity<Void> delete(/* @PathVariable */ Long id) {
        return null;
    }
}

// ============================================================
// Единый обработчик ошибок (каркас)
// ============================================================

// TODO: @RestControllerAdvice
class NoteExceptionHandler07 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class) → 400 + { status, error, fields }
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        return null;
    }

    // TODO: @ExceptionHandler(NoSuchElementException.class) → 404 + { status, error }
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        return null;
    }
}
