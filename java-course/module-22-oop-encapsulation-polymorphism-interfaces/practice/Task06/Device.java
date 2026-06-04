interface Device {
    String name();

    default void info() {
        System.out.println("Устройство: " + name());
    }
}
