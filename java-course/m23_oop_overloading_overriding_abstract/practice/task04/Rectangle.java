package m23_oop_overloading_overriding_abstract.practice.task04;

public class Rectangle extends Figure{

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
        return 2 * (height + width);
    }

    @Override
    public void describe() {
        super.describe();
    }
}
