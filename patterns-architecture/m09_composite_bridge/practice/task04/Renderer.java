package m09_composite_bridge.practice.task04;

// Реализация ("как рисовать") — отдельная иерархия.
interface Renderer {
    String renderCircle(int r);
    String renderSquare(int side);
}
