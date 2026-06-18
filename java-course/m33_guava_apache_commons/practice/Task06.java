package m33_guava_apache_commons.practice;

/**
 * Задача 06 — Модуль 33: Apache Commons (StringUtils, CollectionUtils)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.apache.commons:commons-lang3:3.14.0
 *   org.apache.commons:commons-collections4:4.4
 *
 * ЗАДАНИЕ:
 *   1. StringUtils: продемонстрируйте null-безопасность —
 *      isBlank(null), isBlank("  "), capitalize("привет"),
 *      reverse("abc"), defaultIfBlank("", "—").
 *   2. CollectionUtils: для двух списков выведите union (объединение),
 *      intersection (пересечение) и проверку isEmpty.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   isBlank(null): true
 *   capitalize: Привет
 *   union: [...]
 *   intersection: [...]
 *
 * ПОДСКАЗКА:
 *   StringUtils.isBlank(null) НЕ бросает NPE (в отличие от s.trim()).
 *   CollectionUtils.union(a, b); CollectionUtils.intersection(a, b);
 */
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        System.out.println("=== Apache Commons: StringUtils & CollectionUtils ===\n");

        // 1. Демонстрация StringUtils
        demonstrateStringUtils();

        System.out.println("\n" + "=" .repeat(60));
        System.out.println();

        // 2. Демонстрация CollectionUtils
        demonstrateCollectionUtils();

        System.out.println("\n" + "=" .repeat(60));
        System.out.println();

        // 3. Дополнительные примеры
        demonstrateAdditionalExamples();
    }

    // ============ 1. StringUtils ============
    private static void demonstrateStringUtils() {
        System.out.println("--- 1. StringUtils ---");

        // 1.1. Null-безопасность
        System.out.println("\n1.1. Null-безопасность:");
        String nullString = null;
        String emptyString = "";
        String blankString = "   ";
        String normalString = "Hello";

        System.out.println("   isBlank(null): " + StringUtils.isBlank(nullString));
        System.out.println("   isBlank(\"\"): " + StringUtils.isBlank(emptyString));
        System.out.println("   isBlank(\"   \"): " + StringUtils.isBlank(blankString));
        System.out.println("   isBlank(\"Hello\"): " + StringUtils.isBlank(normalString));

        System.out.println("\n   isEmpty(null): " + StringUtils.isEmpty(nullString));
        System.out.println("   isEmpty(\"\"): " + StringUtils.isEmpty(emptyString));
        System.out.println("   isEmpty(\"   \"): " + StringUtils.isEmpty(blankString));

        // 1.2. Работа со строками
        System.out.println("\n1.2. Работа со строками:");
        System.out.println("   capitalize(\"привет\"): " + StringUtils.capitalize("привет"));
        System.out.println("   capitalize(\"hello world\"): " + StringUtils.capitalize("hello world"));
        System.out.println("   capitalize(null): " + StringUtils.capitalize(null));

        System.out.println("   reverse(\"abc\"): " + StringUtils.reverse("abc"));
        System.out.println("   reverse(\"Hello\"): " + StringUtils.reverse("Hello"));
        System.out.println("   reverse(null): " + StringUtils.reverse(null));

        // 1.3. defaultIfBlank
        System.out.println("\n1.3. defaultIfBlank:");
        System.out.println("   defaultIfBlank(\"\", \"—\"): " + StringUtils.defaultIfBlank("", "—"));
        System.out.println("   defaultIfBlank(\"   \", \"—\"): " + StringUtils.defaultIfBlank("   ", "—"));
        System.out.println("   defaultIfBlank(null, \"—\"): " + StringUtils.defaultIfBlank(null, "—"));
        System.out.println("   defaultIfBlank(\"Hello\", \"—\"): " + StringUtils.defaultIfBlank("Hello", "—"));

        // 1.4. Другие полезные методы
        System.out.println("\n1.4. Другие полезные методы:");
        System.out.println("   defaultIfEmpty(null, \"default\"): " + StringUtils.defaultIfEmpty(null, "default"));
        System.out.println("   defaultIfEmpty(\"\", \"default\"): " + StringUtils.defaultIfEmpty("", "default"));

        System.out.println("   abbreviate(\"This is a long text\", 10): " +
                StringUtils.abbreviate("This is a long text", 10));

        System.out.println("   substringBetween(\"abcde\", \"b\", \"d\"): " +
                StringUtils.substringBetween("abcde", "b", "d"));

        System.out.println("   repeat(\"abc\", 3): " + StringUtils.repeat("abc", 3));

        // 1.5. Сравнение с обычными методами (NullPointerException)
        System.out.println("\n1.5. Сравнение с обычными методами:");
        try {
            System.out.println("   Обычный метод: " + nullString.trim());
        } catch (NullPointerException e) {
            System.out.println("   ❌ NPE при вызове null.trim()");
        }

        try {
            System.out.println("   StringUtils: " + StringUtils.trim(null));
        } catch (Exception e) {
            System.out.println("   ❌ Ошибка: " + e.getMessage());
        }
        System.out.println("   ✅ StringUtils.trim(null) вернул: " + StringUtils.trim(null));

        // 1.6. Работа с регистром
        System.out.println("\n1.6. Работа с регистром:");
        System.out.println("   upperCase(null): " + StringUtils.upperCase(null));
        System.out.println("   upperCase(\"hello\"): " + StringUtils.upperCase("hello"));
        System.out.println("   lowerCase(null): " + StringUtils.lowerCase(null));
        System.out.println("   lowerCase(\"HELLO\"): " + StringUtils.lowerCase("HELLO"));
        System.out.println("   swapCase(\"Hello World\"): " + StringUtils.swapCase("Hello World"));

        // 1.7. Проверка на пустоту
        System.out.println("\n1.7. Проверка на пустоту:");
        System.out.println("   isNotEmpty(\"\"): " + StringUtils.isNotEmpty(""));
        System.out.println("   isNotEmpty(\"Hello\"): " + StringUtils.isNotEmpty("Hello"));
        System.out.println("   isNotBlank(\"   \"): " + StringUtils.isNotBlank("   "));
        System.out.println("   isNotBlank(\"Hello\"): " + StringUtils.isNotBlank("Hello"));
    }

    // ============ 2. CollectionUtils ============
    private static void demonstrateCollectionUtils() {
        System.out.println("--- 2. CollectionUtils ---");

        // Создаем списки
        List<Integer> a = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> b = new ArrayList<>(List.of(3, 4, 5, 6));

        System.out.println("\n2.1. Исходные данные:");
        System.out.println("   Список A: " + a);
        System.out.println("   Список B: " + b);

        // 2.2. Проверка на пустоту
        System.out.println("\n2.2. Проверка на пустоту:");
        System.out.println("   CollectionUtils.isEmpty(a): " + CollectionUtils.isEmpty(a));
        System.out.println("   CollectionUtils.isEmpty(null): " + CollectionUtils.isEmpty(null));
        System.out.println("   CollectionUtils.isNotEmpty(a): " + CollectionUtils.isNotEmpty(a));

        // 2.3. Union (объединение)
        System.out.println("\n2.3. Union (объединение):");
        List<Integer> union = new ArrayList<>(CollectionUtils.union(a, b));
        System.out.println("   union(a, b): " + union);
        System.out.println("   Размер: " + union.size());

        // 2.4. Intersection (пересечение)
        System.out.println("\n2.4. Intersection (пересечение):");
        List<Integer> intersection = new ArrayList<>(CollectionUtils.intersection(a, b));
        System.out.println("   intersection(a, b): " + intersection);
        System.out.println("   Размер: " + intersection.size());

        // 2.5. Другие операции CollectionUtils
        System.out.println("\n2.5. Другие операции:");

        // Difference (разность)
        List<Integer> difference = new ArrayList<>(CollectionUtils.disjunction(a, b));
        System.out.println("   disjunction(a, b) (симметричная разность): " + difference);

        // Subtract (вычитание)
        List<Integer> subtract = new ArrayList<>(CollectionUtils.subtract(a, b));
        System.out.println("   subtract(a, b) (a - b): " + subtract);

        // ContainsAny
        System.out.println("   containsAny(a, [5,6]): " + CollectionUtils.containsAny(a, List.of(5, 6)));
        System.out.println("   containsAny(a, [1,2]): " + CollectionUtils.containsAny(a, List.of(1, 2)));

        // IsEqualCollection
        System.out.println("   isEqualCollection(a, b): " + CollectionUtils.isEqualCollection(a, b));

        List<Integer> c = new ArrayList<>(List.of(1, 2, 3, 4));
        System.out.println("   isEqualCollection(a, c): " + CollectionUtils.isEqualCollection(a, c));

        // 2.6. Работа с null значениями
        System.out.println("\n2.6. Работа с null значениями:");
        List<Integer> withNulls = new ArrayList<>();
        withNulls.add(1);
        withNulls.add(null);
        withNulls.add(2);
        withNulls.add(null);
        withNulls.add(3);
        System.out.println("   Список с null: " + withNulls);

        // 2.7. Card (количество вхождений)
        System.out.println("\n2.7. Кардинальность:");
        System.out.println("   cardinality(3, a): " + CollectionUtils.cardinality(3, a));
        System.out.println("   cardinality(10, a): " + CollectionUtils.cardinality(10, a));

        // 2.8. Select (фильтрация)
        System.out.println("\n2.8. Фильтрация:");
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer num : a) {
            if (num % 2 == 0) {
                evenNumbers.add(num);
            }
        }
        System.out.println("   Четные числа из A: " + evenNumbers);

        // 2.9. Union с множественными значениями
        System.out.println("\n2.9. Union с несколькими списками:");
        List<Integer> d = List.of(7, 8, 9);
        List<Integer> allUnion = new ArrayList<>(CollectionUtils.union(CollectionUtils.union(a, b), d));
        System.out.println("   union(union(a,b), d): " + allUnion);
    }

    // ============ 3. Дополнительные примеры ============
    private static void demonstrateAdditionalExamples() {
        System.out.println("--- 3. Дополнительные примеры ---");

        // 3.1. StringUtils с текстом
        System.out.println("\n3.1. Обработка текста:");
        String text = "  Hello   World  !  ";
        System.out.println("   Исходный текст: '" + text + "'");
        System.out.println("   trim: '" + StringUtils.trim(text) + "'");
        System.out.println("   trimToNull: '" + StringUtils.trimToNull(text) + "'");
        System.out.println("   trimToEmpty: '" + StringUtils.trimToEmpty(text) + "'");

        // 3.2. CollectionUtils с разными типами
        System.out.println("\n3.2. Работа с разными типами коллекций:");
        List<String> names1 = List.of("Alice", "Bob", "Charlie");
        List<String> names2 = List.of("Charlie", "David", "Eve");

        System.out.println("   Names1: " + names1);
        System.out.println("   Names2: " + names2);
        System.out.println("   Union: " + CollectionUtils.union(names1, names2));
        System.out.println("   Intersection: " + CollectionUtils.intersection(names1, names2));

        // 3.3. Практический пример: форматирование строк
        System.out.println("\n3.3. Практический пример:");
        String[] names = {"Иван", "Петр", "Мария", null, "Анна", ""};
        System.out.println("   Обработка массива имен:");
        for (String name : names) {
            String formattedName = StringUtils.defaultIfBlank(name, "Аноним");
            String capitalized = StringUtils.capitalize(formattedName);
            System.out.println("   - " + capitalized);
        }

        // 3.4. CollectionUtils: преобразование списков
        System.out.println("\n3.4. Преобразование коллекций:");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> even = new ArrayList<>();
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                even.add(num);
            }
        }
        System.out.println("   Исходный список: " + numbers);
        System.out.println("   Четные числа: " + even);

        // 3.5. Сравнение производительности
        System.out.println("\n3.5. Сравнение с обычными методами:");
        String testString = null;

        // Обычный способ
        try {
            System.out.println("   Обычный способ: " + testString.isEmpty());
        } catch (NullPointerException e) {
            System.out.println("   ❌ NullPointerException при вызове isEmpty() на null");
        }

        // Apache Commons
        System.out.println("   Apache Commons: " + StringUtils.isEmpty(testString));
        System.out.println("   ✅ Безопасно обрабатывает null");

        // 3.6. Работа с датами (StringUtils)
        System.out.println("\n3.6. Обрезка строк:");
        String longText = "This is a very long text that needs to be abbreviated";
        System.out.println("   Исходный текст: " + longText);
        System.out.println("   Abbreviate (20): " + StringUtils.abbreviate(longText, 20));
        System.out.println("   Abbreviate (30): " + StringUtils.abbreviate(longText, 30));
        System.out.println("   Abbreviate (50): " + StringUtils.abbreviate(longText, 50));

        // 3.7. CollectionUtils с пустыми коллекциями
        System.out.println("\n3.7. Работа с пустыми коллекциями:");
        List<Integer> emptyList = new ArrayList<>();
        List<Integer> nullList = null;

        System.out.println("   isEmpty(emptyList): " + CollectionUtils.isEmpty(emptyList));
        System.out.println("   isEmpty(nullList): " + CollectionUtils.isEmpty(nullList));
        System.out.println("   isNotEmpty(emptyList): " + CollectionUtils.isNotEmpty(emptyList));
        System.out.println("   sizeIsEmpty(emptyList): " + CollectionUtils.sizeIsEmpty(emptyList));
        System.out.println("   size(emptyList): " + CollectionUtils.size(emptyList));
        System.out.println("   size(nullList): " + CollectionUtils.size(nullList));
    }
}
