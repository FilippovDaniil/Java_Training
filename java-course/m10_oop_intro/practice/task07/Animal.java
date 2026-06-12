package m10_oop_intro.practice.task07;

class Animal {
    String name;
    int age;

    void describe() {
        System.out.println(name + ", возраст " + age);
    }

    void makeSound() {
        System.out.println(name + " издаёт звук");
    }
}
