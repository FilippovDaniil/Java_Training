package m08_proxy_facade.practice.task01;

class RealImage implements Image {
    // TODO: поле file

    public RealImage(String file) {
        // TODO: задать file и напечатать "загрузка " + file (имитация дорогой операции)
    }

    @Override
    public void display() {
        // TODO: напечатать "показ " + file
    }
}
