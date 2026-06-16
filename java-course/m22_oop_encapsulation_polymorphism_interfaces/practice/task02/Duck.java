package m22_oop_encapsulation_polymorphism_interfaces.practice.task02;

public class Duck implements Flyable, Swimmable{

    private String name;

    public Duck(String name) {
        this.name = name;
    }

    @Override
    public void fly() {
        System.out.println(name + " летает");
    }

    @Override
    public void swim() {
        System.out.println(name + " плавает");
    }
}
