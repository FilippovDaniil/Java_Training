package m30_design_patterns.practice.task02;

public class NotificationFactory {
    public static Notification create (String type){
        return switch (type){
            case "Push" -> new PushNotification();
            case "Sms" -> new SmsNotification();
            case "Email" -> new EmailNotification();
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}
