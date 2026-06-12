package m06_prototype_pool.practice.task01;

import java.util.ArrayList;
import java.util.List;

class Document {
    // TODO: поля title (String) и tags (List<String>)

    public Document(String title) {
        // TODO: задать title, инициализировать пустой список тегов
    }

    // copy-конструктор: ГЛУБОКАЯ копия (новый список тегов)
    public Document(Document other) {
        // TODO: скопировать title; tags = new ArrayList<>(other.tags)
    }

    public void addTag(String tag) {
        // TODO
    }

    public List<String> getTags() {
        // TODO
        return List.of();
    }

    public String getTitle() {
        // TODO
        return "";
    }
}
