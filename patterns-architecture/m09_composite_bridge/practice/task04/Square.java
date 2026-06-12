package m09_composite_bridge.practice.task04;

class Square extends Shape {
    private final int side;

    public Square(int side, Renderer renderer) {
        super(renderer);
        this.side = side;
    }

    @Override
    public String draw() {
        // TODO: делегировать renderer.renderSquare(side)
        return "";
    }
}
