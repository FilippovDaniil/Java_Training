package m30_design_patterns.practice.task02;

public class EmailNotification implements Notification{
    @Override
    public void send(String message) {
        System.out.println("Отправка по email");
    }
}
