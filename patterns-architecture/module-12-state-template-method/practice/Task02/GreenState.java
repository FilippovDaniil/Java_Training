class GreenState implements TrafficLightState {
    @Override
    public TrafficLightState next() {
        // TODO: new YellowState()
        return this;
    }

    @Override
    public String color() {
        // TODO: "GREEN"
        return "";
    }
}
