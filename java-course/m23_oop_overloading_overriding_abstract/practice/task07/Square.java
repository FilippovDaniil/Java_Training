package m23_oop_overloading_overriding_abstract.practice.task07;

public class Square extends Shape{

    private double side;

    Square(String name, double side) {
        super(name);
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public double perimeter() {
        return side * 4;
    }
}
