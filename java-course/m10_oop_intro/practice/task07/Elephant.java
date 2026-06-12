package m10_oop_intro.practice.task07;

class Elephant extends Animal {
    int trunkLength;

    Elephant(String name, int age, int trunkLength) {
        super(name, age);
        this.trunkLength = trunkLength;
    }

    void spray() {
        System.out.println(name + " поливает водой");
    }
}
