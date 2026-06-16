package m23_oop_overloading_overriding_abstract.practice.task03;

public class Square extends Shape{
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    double area() {
        return side*side;
    }

    @Override
    public void printArea() {
        super.printArea();
    }
}
