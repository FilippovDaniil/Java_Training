package m29_gc_reference_types.practice.task01;

import java.lang.ref.Cleaner;

public class Resource {
    private final String name;
    private final Cleaner.Cleanable cleanable;

    Resource(String name) {
        this.name = name;
        System.out.println("Создан ресурс: " + name);

        // Регистрируем очистку
        cleanable = Cleaner.create().register(this, () -> {
            System.out.println("Ресурс '" + name + "' будет удален сборщиком мусора (Cleaner)");
        });
    }

    void use() {
        System.out.println("Использование ресурса: " + name);
    }

    String getName() {
        return name;
    }
}