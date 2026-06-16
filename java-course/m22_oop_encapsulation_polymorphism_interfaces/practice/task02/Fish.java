package m22_oop_encapsulation_polymorphism_interfaces.practice.task02;

public class Fish implements Swimmable{
    private String name;

    public Fish(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
        System.out.println(name + " плавает");
    }
}
