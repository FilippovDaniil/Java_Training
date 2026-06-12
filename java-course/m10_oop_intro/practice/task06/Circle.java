package m10_oop_intro.practice.task06;

class Circle extends Shape {
    // TODO: наследовать Shape, добавить поле radius

    private int radius;

    public Circle(String name, int radius) {
        super(name);
        this.radius = radius;
    }
}
