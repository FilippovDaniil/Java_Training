package m10_oop_intro.practice.task06;

class Square extends Shape {
    // TODO: наследовать Shape, добавить поле side

    private int side;

    public Square(String name, int side) {
        super(name);
        this.side = side;
    }
}
