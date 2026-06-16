package m22_oop_encapsulation_polymorphism_interfaces.practice.task01;

public class RussianPerson implements Greetable{
    @Override
    public void greet() {
        System.out.println("Привет !");
    }
}
