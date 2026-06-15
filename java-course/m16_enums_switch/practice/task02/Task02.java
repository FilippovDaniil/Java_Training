package m16_enums_switch.practice.task02;

/**
 * Задача 02 — Модуль 16: switch по enum
 *
 * ЗАДАНИЕ:
 *   Дан enum TrafficLight (RED, YELLOW, GREEN). Напишите метод
 *   action(TrafficLight light), который через switch выводит:
 *     RED    → "Стоп"
 *     YELLOW → "Внимание"
 *     GREEN  → "Можно ехать"
 *   Вызовите метод для всех трёх цветов.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Стоп
 *   Внимание
 *   Можно ехать
 *
 * ПОДСКАЗКА:
 *   В case пишите имя без префикса: case RED:
 */

public class Task02 {
    public static void main(String[] args) {
        // Вызовите action для RED, YELLOW, GREEN
        System.out.println(action(TrafficLight.RED));
        System.out.println(action(TrafficLight.YELLOW));
        System.out.println(action(TrafficLight.GREEN));

    }

    // TODO: метод action(TrafficLight light)
    private static String action(TrafficLight trafficLight){
        String otvet = switch (trafficLight){
            case RED -> "Stop";
            case GREEN -> "You can ride";
            case YELLOW -> "Vnimanie";
        };
        return otvet;
    }
}
