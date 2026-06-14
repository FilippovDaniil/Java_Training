package m11_objects_constructors.practice.task01;

class Point {
    private int x;
    private int y;

    // TODO: добавьте конструктор Point(int x, int y)


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
