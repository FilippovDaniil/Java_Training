package m16_enums_switch.practice.task06;

enum Logger {
    INSTANCE;

    private int count = 0;

    // TODO: метод log(String message)

    public void log (String message){
        count++;
        System.out.println("[" + count + "] " + message);
    }
}
