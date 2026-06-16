package m23_oop_overloading_overriding_abstract.practice.task04;

abstract class Figure {
    protected String name;

    Figure(String name) {
        this.name = name;
    }

    protected abstract double area();
    protected abstract double perimeter();

    public void describe() {
        System.out.printf("%s: площадь=%.2f, периметр=%.2f%n", name, area(), perimeter());
    }
}
