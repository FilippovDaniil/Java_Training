// Ядро: зависит только от порта NotificationPort, не от адаптера.
class AlertService {
    // TODO: поле final NotificationPort port + конструктор AlertService(NotificationPort port)

    public void raise(String reason) {
        // TODO: port.send("ALERT: " + reason)
    }
}
