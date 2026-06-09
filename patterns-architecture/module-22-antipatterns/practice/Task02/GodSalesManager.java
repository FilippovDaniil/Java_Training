// «ДО» — God Object: и считает, и форматирует (две несвязанные обязанности).
// НЕ используйте в решении; оставлено как образец антипаттерна.
class GodSalesManager {
    long total(long[] sales) {
        long sum = 0;
        for (long s : sales) sum += s;
        return sum;
    }

    String report(long[] sales) {
        long sum = 0;
        for (long s : sales) sum += s;
        return "Итого продаж: " + sum;
    }
}
