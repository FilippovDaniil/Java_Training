package m23_oop_overloading_overriding_abstract.practice.task06;

class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // TODO: toString(); перегруженные add(Vector2D) и add(int, int)
    public Vector2D add(Vector2D vector2D){
        return new Vector2D(x + vector2D.getX(),y + vector2D.getY());
    }

    public Vector2D add(int a, int b){
        return new Vector2D(x + a,y + b);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
