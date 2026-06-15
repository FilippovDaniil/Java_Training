package m16_enums_switch.practice.task04;

enum Operation {
    PLUS, MINUS, MULTIPLY, DIVIDE;

    // TODO: метод int apply(int a, int b)
    public int apply(int a, int b){
        switch (this){
            case PLUS -> {
                return a + b;
            }
            case MINUS -> {
                return a - b;
            }
            case MULTIPLY -> {
                return a * b;
            }
            case DIVIDE -> {
                return a / b;
            }
            default -> {
                return 0;
            }
        }
    }
}
