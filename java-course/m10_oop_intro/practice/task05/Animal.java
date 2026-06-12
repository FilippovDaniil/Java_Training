package m10_oop_intro.practice.task05;

class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " est");
    }
}
