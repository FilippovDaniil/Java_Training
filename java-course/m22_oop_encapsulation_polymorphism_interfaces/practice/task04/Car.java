package m22_oop_encapsulation_polymorphism_interfaces.practice.task04;

class Car {
    // TODO: private final Engine engine = new Engine();
    //       методы start() и stop() с делегированием
    private final Engine engine = new Engine();

    public void start(){
        engine.start();
        System.out.println("Car is running");
    }

    public void stop(){
        engine.stop();
        System.out.println("Car is stopping");
    }
}
