package m26_legacy_refactoring_capstone.practice.task06;

// Шов: за этим интерфейсом прячется источник числа (можно подменить в тесте).
interface NumberSource {
    int next();
}
