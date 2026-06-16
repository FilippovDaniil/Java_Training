package m22_oop_encapsulation_polymorphism_interfaces.practice.task06;

public class Telephone implements Device{
    @Override
    public String name() {
        return "Mobile phone";
    }

    @Override
    public void info() {
        Device.super.info();
    }
}
