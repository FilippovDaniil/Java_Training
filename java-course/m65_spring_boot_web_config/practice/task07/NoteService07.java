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
// Сервис (каркас)
// ============================================================

// TODO: @Service
@Service
class NoteService07 {

    private final Map<Long, Note> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Note create(String text) {
        // TODO: id = seq.getAndIncrement(); создать Note; положить в store; вернуть
        long id = seq.getAndIncrement();
        Note note = new Note(id,text);
        store.put(id,note);
        return note;
    }

    public List<Note> findAll() {
        // TODO: верните new ArrayList<>(store.values())
        return new ArrayList<>(store.values());
    }

    public Optional<Note> findById(Long id) {
        // TODO: Optional.ofNullable(store.get(id))
        return Optional.ofNullable(store.get(id));
    }

    public Note update(Long id, String text) {
        // TODO: если нет — throw new NoSuchElementException("Заметка не найдена: " + id)
        if(findById(id).isEmpty()){
            throw new NoSuchElementException("Заметка не найдена: " + id);
        }
        // TODO: создать обновлённую Note, положить, вернуть
        else{
            Note note = new Note(id,text);
            store.put(id,note);
            return note;
        }
    }

    public void delete(Long id) {
        // TODO: если store.remove(id) == null — throw new NoSuchElementException(...)
        if(findById(id).isEmpty()){
            throw new NoSuchElementException("Заметка не найдена: " + id);
        }
        // TODO: создать обновлённую Note, положить, вернуть
        else{
            store.remove(id);
            System.out.println("Заметка удалена по id: " + id);
        }
    }
}
