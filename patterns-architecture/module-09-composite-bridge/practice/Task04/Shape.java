// Абстракция ("что рисуем") хранит ссылку на реализацию — это «мост».
abstract class Shape {
    protected final Renderer renderer;

    protected Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String draw();
}
