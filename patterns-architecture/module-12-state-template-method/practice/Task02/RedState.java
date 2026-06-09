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
