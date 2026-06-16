package m23_oop_overloading_overriding_abstract.practice.task07;

public class Rectangle extends Shape{
    private double width;
    private double height;

    Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return (width + height) * 2;
    }
}
