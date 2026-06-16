package m23_oop_overloading_overriding_abstract.practice.task03;

abstract class Shape {
    abstract double area();

    public void printArea() {
        System.out.println("Площадь: " + area());
    }
}
