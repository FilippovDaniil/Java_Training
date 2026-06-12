package m08_proxy_facade.practice.task01;

// Виртуальный прокси: создаёт RealImage лениво, при первом display().
class ImageProxy implements Image {
    // TODO: поля String file и RealImage real (изначально null) + конструктор ImageProxy(String file)

    @Override
    public void display() {
        // TODO: if (real == null) real = new RealImage(file); затем real.display()
    }
}
