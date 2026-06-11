package m65_spring_boot_web_config.practice.task07;

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
