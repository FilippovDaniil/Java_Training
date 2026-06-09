/**
 * Задача 05 — Тема 09: Bridge (пульт × устройство)
 *
 * ЗАДАНИЕ:
 *   Разнесите «пульт» (абстракция) и «устройство» (реализация):
 *     - Device (файл Device.java): String name(); String turnOn(); String turnOff();
 *       реализации TV и Radio;
 *     - RemoteControl (файл RemoteControl.java) — абстракция: хранит Device и
 *       состояние on; togglePower() переключает питание (возвращает turnOn()/turnOff());
 *     - BasicRemote (файл BasicRemote.java) — простой пульт;
 *       AdvancedRemote (файл AdvancedRemote.java) — добавляет mute()
 *       → "mute " + device.name().
 *   В main: BasicRemote с TV (включить/выключить), AdvancedRemote с Radio (mute).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   TV on
 *   TV off
 *   mute Radio
 *
 * ТРЕБОВАНИЯ:
 *   - любой пульт работает с любым устройством (комбинируются свободно);
 *   - RemoteControl делегирует Device через ссылку (мост), не наследует его;
 *   - новое устройство или новый пульт добавляются без классов вида BasicTvRemote.
 *
 * ПОДСКАЗКА:
 *   togglePower(): если выключено → on=true, return device.turnOn(); иначе
 *   on=false, return device.turnOff().
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: BasicRemote(new TV()) — togglePower дважды; AdvancedRemote(new Radio()) — mute()
    }
}
