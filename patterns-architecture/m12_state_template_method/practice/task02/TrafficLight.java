package m12_state_template_method.practice.task02;

class TrafficLight {
    private TrafficLightState state = new RedState();

    public void next() {
        // TODO: state = state.next()
    }

    public String color() {
        // TODO: state.color()
        return "";
    }
}
