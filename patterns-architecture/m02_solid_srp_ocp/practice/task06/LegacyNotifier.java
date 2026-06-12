package m02_solid_srp_ocp.practice.task06;

/**
 * «ДО» — рабочий, но плохой класс. НЕ дорабатывайте его и не используйте
 * в решении: он оставлен как образец того, что мы рефакторим.
 *
 * Проблемы:
 *   1. OCP: switch по типу канала — новый канал требует правки этого метода.
 *   2. SRP: один метод и выбирает канал, и форматирует, и «отправляет».
 */
class LegacyNotifier {
    void notify(String channel, String text) {
        switch (channel) {
            case "email" -> System.out.println("[email] " + text);
            case "sms"   -> System.out.println("[sms] " + text);
            default -> throw new IllegalArgumentException("Неизвестный канал: " + channel);
        }
    }
}
