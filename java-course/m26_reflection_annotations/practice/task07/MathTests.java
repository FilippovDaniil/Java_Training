package m26_reflection_annotations.practice.task07;

public class MathTests {

    // Вспомогательный метод для проверки условий
    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new RuntimeException(message + " (ожидалось " + expected + ", получено " + actual + ")");
        }
    }

    @MyTest
    public void testAddition() {
        int sum = 2 + 2;
        assertEquals(4, sum, "Сложение работает неверно");
    }

    @MyTest
    public void testDivision() {
        int result = 10 / 2;
        assertEquals(5, result, "Деление работает неверно");
    }

    @MyTest
    public void testMax() {
        int max = Math.max(5, 3);
        assertEquals(5, max, "Максимум вычислен неверно");
    }

    @MyTest
    public void testFailing() {
        int value = 3 * 3;
        assertEquals(10, value, "Умышленно падающий тест");
    }
}