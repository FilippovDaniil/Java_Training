package m22_oop_encapsulation_polymorphism_interfaces.practice.task02;

public class Plane implements Flyable{
    private String name;

    public Plane(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " летает");
    }
}
