// Penguin — птица, но НЕ Flyer. Поэтому ему не нужно «выключать» полёт
// заглушкой: его просто нельзя попросить лететь через тип Flyer (LSP соблюдён).
class Penguin implements Bird {
    @Override
    public String name() {
        // TODO
        return "";
    }

    @Override
    public void eat() {
        // TODO: вывести что пингвин ест
    }
}
