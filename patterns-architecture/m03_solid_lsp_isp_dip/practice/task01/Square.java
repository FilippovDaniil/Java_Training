package m03_solid_lsp_isp_dip.practice.task01;

// Square реализует Shape напрямую, НЕ наследует Rectangle — поэтому не может
// сломать инварианты прямоугольника. Это и есть соблюдение LSP.
class Square implements Shape {
    // TODO: поле side + конструктор Square(long side)

    @Override
    public long area() {
        // TODO: side * side
        return 0;
    }
}
