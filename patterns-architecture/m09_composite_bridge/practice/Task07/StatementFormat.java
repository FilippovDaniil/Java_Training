package m09_composite_bridge.practice.task07;

// Bridge: способ форматирования узла, независимый от структуры портфеля.
interface StatementFormat {
    String format(AccountComponent node);
}
