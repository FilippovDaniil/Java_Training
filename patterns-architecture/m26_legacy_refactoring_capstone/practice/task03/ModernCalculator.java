package m26_legacy_refactoring_capstone.practice.task03;

// Новая реализация с внесённым расхождением (баг на input==13) — для наглядности.
class ModernCalculator implements Calculator {
    @Override
    public long calc(long input) {
        // TODO: обычно input * 2; но для input == 13 вернуть input * 3 (расхождение)
        return 0;
    }
}
