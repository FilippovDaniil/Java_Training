package m22_oop_encapsulation_polymorphism_interfaces.practice.task06;

public class Laptop implements Device{
    @Override
    public String name() {
        return "Laptop";
    }

    @Override
    public void info() {
        System.out.println(this.name() + " - " + "Mobile computer");
    }
}
