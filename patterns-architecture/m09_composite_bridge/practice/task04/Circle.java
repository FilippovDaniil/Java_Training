package m09_composite_bridge.practice.task04;

class Circle extends Shape {
    private final int r;

    public Circle(int r, Renderer renderer) {
        super(renderer);
        this.r = r;
    }

    @Override
    public String draw() {
        // TODO: делегировать renderer.renderCircle(r)
        return "";
    }
}
