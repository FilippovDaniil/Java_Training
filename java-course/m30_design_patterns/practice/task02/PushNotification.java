package m30_design_patterns.practice.task02;

public class PushNotification implements Notification{
    @Override
    public void send(String message) {
        System.out.println("Отправка через пуш приложения");
    }
}
