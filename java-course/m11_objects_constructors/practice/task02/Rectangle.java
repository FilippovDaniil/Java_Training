package m11_objects_constructors.practice.task02;

class Rectangle {
    private int width;
    private int height;

    // TODO: два конструктора и метод area()


    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(int side) {
        this(side,side);
    }

    public double area(){
        return width*height;
    }
}
