package m30_design_patterns.practice.task02;

public class SmsNotification implements Notification{
    @Override
    public void send(String message) {
        System.out.println("Отправка через SMS");
    }
}
