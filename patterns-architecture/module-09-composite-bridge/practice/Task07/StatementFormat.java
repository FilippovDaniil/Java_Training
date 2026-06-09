// Bridge: способ форматирования узла, независимый от структуры портфеля.
interface StatementFormat {
    String format(AccountComponent node);
}
