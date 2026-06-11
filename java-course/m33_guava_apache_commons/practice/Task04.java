package m33_guava_apache_commons.practice;

/**
 * Задача 04 — Модуль 33: Immutable-коллекции и Preconditions
 *
 * ТРЕБУЕТСЯ ЗАВИСИМОСТЬ: com.google.guava:guava:33.2.1-jre
 *
 * ЗАДАНИЕ:
 *   1. Создайте неизменяемый список ImmutableList и неизменяемую карту
 *      ImmutableMap. Попробуйте их изменить (add/put) и убедитесь, что
 *      бросается UnsupportedOperationException (поймайте его).
 *   2. Напишите метод register(String name, int age), который через
 *      Preconditions.checkArgument проверяет: name не пустой, age в
 *      диапазоне 0..120. При нарушении — понятное исключение.
 *   3. Вызовите метод с корректными и некорректными данными.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Список неизменяем: поймано исключение
 *   Зарегистрирован: Иван (30)
 *   Ошибка: Возраст вне диапазона: 200
 *
 * ПОДСКАЗКА:
 *   ImmutableList.of("a","b");  ImmutableMap.of("k", 1);
 *   checkArgument(age >= 0 && age <= 120, "Возраст вне диапазона: %s", age);
 */
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import static com.google.common.base.Preconditions.checkArgument;

public class Task04 {
    public static void main(String[] args) {
        // Поработайте с Immutable-коллекциями и Preconditions
    }

    // TODO: метод register(String name, int age) с checkArgument
}
