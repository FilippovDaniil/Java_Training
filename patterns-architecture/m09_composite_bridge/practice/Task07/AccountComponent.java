package m09_composite_bridge.practice.task07;

// Composite: общий интерфейс счёта (листа) и портфеля (контейнера).
interface AccountComponent {
    String name();
    long totalBalanceCents();
}
