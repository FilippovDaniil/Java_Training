package m65_spring_boot_web_config.practice.task07;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/api/notes")
class NoteController07 {

    // TODO: внедрите NoteService07 через конструктор
    private final NoteService07 service;

    @Autowired
    public NoteController07(NoteService07 service) {
        this.service = service;
    }

    // TODO: @PostMapping → 201 Created + тело
    @PostMapping
    public ResponseEntity<Note> create(/* @Valid @RequestBody */ @Valid @RequestBody CreateNoteDto dto) {
        Note saved = service.create(dto.text());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // TODO: @GetMapping → 200 + список
    @GetMapping
    public List<Note> all() {
        return service.findAll();
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    @GetMapping("/{id}")
    public ResponseEntity<Note> one(/* @PathVariable */ @PathVariable("id") Long id) {
        // TODO: service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build())
        return service.findById(id)
                .map(ResponseEntity::ok)                                // 200
                .orElse(ResponseEntity.notFound().build());             // 404
    }

    // TODO: @PutMapping("/{id}") → 200 + обновлённая
    @PutMapping("/{id}")
    public Note update(/* @PathVariable */@PathVariable("id") Long id, /* @Valid @RequestBody */ @Valid @RequestBody CreateNoteDto dto) {
        return service.update(id,dto.text());
    }

    // TODO: @DeleteMapping("/{id}") → 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(/* @PathVariable */ @PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();    // 204
        } catch (Exception e) {
            return ResponseEntity.notFound().build();     // 404
        }
    }
}
