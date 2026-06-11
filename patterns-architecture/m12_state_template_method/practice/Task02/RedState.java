package m12_state_template_method.practice.task02;

class RedState implements TrafficLightState {
    @Override
    public TrafficLightState next() {
        // TODO: new GreenState()
        return this;
    }

    @Override
    public String color() {
        // TODO: "RED"
        return "";
    }
}
