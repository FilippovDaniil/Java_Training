package m16_enums_switch.practice.task03;

enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    // TODO: геттер getValue()


    public int getValue() {
        return value;
    }
}
