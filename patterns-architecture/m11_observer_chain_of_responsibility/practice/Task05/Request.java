package m11_observer_chain_of_responsibility.practice.task05;

/** Заявка — данные (готово). */
class Request {
    private final String name;
    private final int age;

    Request(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() { return name; }
    int getAge() { return age; }
}
