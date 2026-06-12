package m10_oop_intro.practice.task05;

class Dog extends Animal  {
    // TODO: наследовать Animal и добавить метод bark()


    public Dog(String name) {
        super(name);
    }

    public void bark(){
        System.out.println("Bark");
    }
}
