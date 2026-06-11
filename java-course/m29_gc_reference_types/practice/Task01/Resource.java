package m29_gc_reference_types.practice.task01;

class Resource {
    private final String name;

    Resource(String name) {
        this.name = name;
        System.out.println("Создан ресурс: " + name);
    }
}
