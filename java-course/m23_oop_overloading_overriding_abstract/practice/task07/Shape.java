package m23_oop_overloading_overriding_abstract.practice.task07;

abstract class Shape {

    protected String name;

    Shape(String name) {
        this.name = name;
    }

    protected abstract double area();
    protected abstract double perimeter();

    public boolean isLargerThan(Shape other) {
        return this.area() > other.area();
    }

    public String getName() {
        return name;
    }
}
