package m23_oop_overloading_overriding_abstract.practice.task04;

abstract class Figure {
    String name;

    Figure(String name) {
        this.name = name;
    }

    abstract double area();
    abstract double perimeter();

    void describe() {
        System.out.printf("%s: площадь=%.2f, периметр=%.2f%n", name, area(), perimeter());
    }
}
