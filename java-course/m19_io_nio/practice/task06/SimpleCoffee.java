package m19_io_nio.practice.task06;

public class SimpleCoffee implements Coffee{
    @Override
    public String description() {
        return "Kofe";
    }

    @Override
    public double cost() {
        return 100;
    }
}
