package m29_gc_reference_types.practice;

/**
 * Задача 04 — Модуль 29: WeakHashMap
 *
 * ЗАДАНИЕ:
 *   Покажите автоматическое удаление записей из WeakHashMap.
 *   1. Создайте WeakHashMap, положите 2-3 записи, где ключи — объекты,
 *      на которые есть strong-ссылки (переменные).
 *   2. Выведите размер карты.
 *   3. Обнулите strong-ссылки на часть ключей, вызовите System.gc(),
 *      сделайте паузу.
 *   4. Снова выведите размер — записи с собранными ключами исчезнут.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Размер до GC: 3
 *   Размер после GC: 1
 *
 * ПОДСКАЗКА:
 *   Map<Object, String> map = new WeakHashMap<>();
 *   Сохраните один ключ в переменной (его запись не исчезнет),
 *   остальные обнулите.
 */
import java.util.Map;
import java.util.WeakHashMap;

public class Task04 {
    public static void main(String[] args) throws InterruptedException {
        // Создайте WeakHashMap, обнулите часть ключей, вызовите GC, сравните размеры
    }
}
