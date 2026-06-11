package m09_composite_bridge.practice.task06;

// Лист Composite + мост к Renderer (Bridge).
class ShapeGraphic implements Graphic {
    private final String name;
    private final Renderer renderer;

    public ShapeGraphic(String name, Renderer renderer) {
        this.name = name;
        this.renderer = renderer;
    }

    @Override
    public String render() {
        // TODO: делегировать renderer.draw(name)
        return "";
    }
}
