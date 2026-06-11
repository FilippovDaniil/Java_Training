package m12_state_template_method.practice.task02;

interface TrafficLightState {
    TrafficLightState next();
    String color();
}
