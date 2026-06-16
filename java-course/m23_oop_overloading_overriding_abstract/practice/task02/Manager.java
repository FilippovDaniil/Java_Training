package m23_oop_overloading_overriding_abstract.practice.task02;

public class Manager extends Employee{

    Manager(String name) {
        super(name);
    }

    @Override
    void describe() {
        super.describe();
        System.out.println("Должность: менеджер");
    }
}
