package m09_composite_bridge.practice.task05;

// Расширенный пульт — добавляет mute(), работает с любым Device.
class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public String mute() {
        // TODO: вернуть "mute " + device.name()
        return "";
    }
}
