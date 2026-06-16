package m23_oop_overloading_overriding_abstract.practice.task07;

public class Circle extends Shape{
    private double radius;
    private final double PI = 3.14;

    Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double area() {
        return radius * radius * PI;
    }

    @Override
    public double perimeter() {
        return 2 * PI * radius;
    }
}
