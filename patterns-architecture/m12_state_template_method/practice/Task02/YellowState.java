package m12_state_template_method.practice.task02;

class YellowState implements TrafficLightState {
    @Override
    public TrafficLightState next() {
        // TODO: new RedState() (цикл замыкается)
        return this;
    }

    @Override
    public String color() {
        // TODO: "YELLOW"
        return "";
    }
}
