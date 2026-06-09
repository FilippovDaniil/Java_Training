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
