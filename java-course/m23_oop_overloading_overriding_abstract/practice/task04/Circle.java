package m23_oop_overloading_overriding_abstract.practice.task04;

import org.springframework.security.access.method.P;

public class Circle extends Figure{

    private double radius;
    private final double PI = 3.14;

    @Override
    public double area() {
        return radius * radius * PI;
    }

    Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double perimeter() {
        return 2 * PI * radius;
    }

    @Override
    public void describe() {
        super.describe();
    }
}
