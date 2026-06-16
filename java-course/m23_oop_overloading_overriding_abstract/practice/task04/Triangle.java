package m23_oop_overloading_overriding_abstract.practice.task04;

public class Triangle extends Figure{

    private double a;
    private double b;
    private double c;

    Triangle(String name, double a, double b, double c) {
        super(name);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        // Проверка существования треугольника
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Треугольник не существует");
        }
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public void describe() {
        super.describe();
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }
}
