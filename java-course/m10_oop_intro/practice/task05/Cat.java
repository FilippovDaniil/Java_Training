package m10_oop_intro.practice.task05;

class Cat extends Animal  {
    // TODO: наследовать Animal и добавить метод meow()


    public Cat(String name) {
        super(name);
    }

    public void meow(){
        System.out.println("Meow");
    }
}
