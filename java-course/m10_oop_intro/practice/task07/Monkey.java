package m10_oop_intro.practice.task07;

class Monkey extends Animal {
    Monkey(String name, int age) {
        super(name, age);
    }

    void climb() {
        System.out.println(name + " залезает на дерево");
    }
}
