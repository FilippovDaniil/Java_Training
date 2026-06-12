package m10_oop_intro.practice.task07;

class Lion extends Animal {
    Lion(String name, int age) {
        super(name, age);
    }

    void hunt() {
        System.out.println(name + " охотится");
    }
}
