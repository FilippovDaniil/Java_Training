package m09_composite_bridge.practice.task05;

// Абстракция ("чем управляем извне") хранит ссылку на устройство — мост.
abstract class RemoteControl {
    protected final Device device;
    protected boolean on = false;

    protected RemoteControl(Device device) {
        this.device = device;
    }

    public String togglePower() {
        // TODO: если выключено → on=true, вернуть device.turnOn();
        //       иначе → on=false, вернуть device.turnOff()
        return "";
    }
}
