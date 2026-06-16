package m23_oop_overloading_overriding_abstract.practice.task02;

class Employee {
    private String name;

    Employee(String name) {
        this.name = name;
    }

    void describe() {
        System.out.println("Сотрудник: " + name);
    }
}
