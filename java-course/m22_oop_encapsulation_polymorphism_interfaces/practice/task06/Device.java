package m22_oop_encapsulation_polymorphism_interfaces.practice.task06;

interface Device {
    String name();

    default void info() {
        System.out.println("Устройство: " + name());
    }
}
