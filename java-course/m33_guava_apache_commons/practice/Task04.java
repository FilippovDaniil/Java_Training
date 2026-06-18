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

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Task04 {

    // Хранилище зарегистрированных пользователей
    private static final List<String> registeredUsers = new java.util.ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Демонстрация Immutable-коллекций ===\n");

        // 1. Работа с ImmutableList
        demonstrateImmutableList();

        System.out.println("\n" + "=" .repeat(60));
        System.out.println();

        // 2. Работа с ImmutableMap
        demonstrateImmutableMap();

        System.out.println("\n" + "=" .repeat(60));
        System.out.println();

        // 3. Работа с Preconditions
        demonstratePreconditions();

        System.out.println("\n" + "=" .repeat(60));
        System.out.println();

        // 4. Регистрация пользователей
        demonstrateRegistration();
    }

    // ============ 1. ImmutableList ============
    private static void demonstrateImmutableList() {
        System.out.println("--- 1. ImmutableList ---");

        // Создаем неизменяемый список
        ImmutableList<String> immutableList = ImmutableList.of("Java", "Python", "C++", "JavaScript");
        System.out.println("Создан ImmutableList: " + immutableList);
        System.out.println("Размер: " + immutableList.size());
        System.out.println("Содержит 'Java': " + immutableList.contains("Java"));
        System.out.println("Элемент по индексу 2: " + immutableList.get(2));

        // Пытаемся изменить список
        System.out.println("\nПопытка изменить ImmutableList:");
        try {
            // Это вызовет UnsupportedOperationException
            immutableList.add("Go");
            System.out.println("   Успешно добавлено (не должно произойти)");
        } catch (UnsupportedOperationException e) {
            System.out.println("   ✅ Поймано исключение: " + e.getClass().getSimpleName());
            System.out.println("   Сообщение: " + e.getMessage());
            System.out.println("   Нельзя изменять ImmutableList!");
        }

        try {
            // Это тоже вызовет исключение
            immutableList.remove(0);
            System.out.println("   Успешно удалено (не должно произойти)");
        } catch (UnsupportedOperationException e) {
            System.out.println("   ✅ Поймано исключение при попытке удаления");
        }

        // Создание ImmutableList из существующего списка
        List<String> mutableList = new java.util.ArrayList<>();
        mutableList.add("Apple");
        mutableList.add("Banana");
        mutableList.add("Cherry");

        ImmutableList<String> copyFromMutable = ImmutableList.copyOf(mutableList);
        System.out.println("\nImmutableList из обычного списка: " + copyFromMutable);

        // Builder для создания больших списков
        ImmutableList<String> builtList = ImmutableList.<String>builder()
                .add("One")
                .add("Two")
                .add("Three")
                .add("Four")
                .add("Five")
                .build();
        System.out.println("ImmutableList через Builder: " + builtList);
    }

    // ============ 2. ImmutableMap ============
    private static void demonstrateImmutableMap() {
        System.out.println("--- 2. ImmutableMap ---");

        // Создаем неизменяемую карту
        ImmutableMap<String, Integer> immutableMap = ImmutableMap.of(
                "Иван", 25,
                "Мария", 30,
                "Петр", 35,
                "Анна", 28
        );
        System.out.println("Создан ImmutableMap: " + immutableMap);
        System.out.println("Размер: " + immutableMap.size());
        System.out.println("Возраст Марии: " + immutableMap.get("Мария"));
        System.out.println("Содержит ключ 'Петр': " + immutableMap.containsKey("Петр"));

        // Пытаемся изменить карту
        System.out.println("\nПопытка изменить ImmutableMap:");
        try {
            // Это вызовет UnsupportedOperationException
            immutableMap.put("Ольга", 22);
            System.out.println("   Успешно добавлено (не должно произойти)");
        } catch (UnsupportedOperationException e) {
            System.out.println("   ✅ Поймано исключение: " + e.getClass().getSimpleName());
            System.out.println("   Нельзя изменять ImmutableMap!");
        }

        try {
            // Это тоже вызовет исключение
            immutableMap.remove("Иван");
            System.out.println("   Успешно удалено (не должно произойти)");
        } catch (UnsupportedOperationException e) {
            System.out.println("   ✅ Поймано исключение при попытке удаления");
        }

        // Создание ImmutableMap из существующей карты
        Map<String, Integer> mutableMap = new java.util.HashMap<>();
        mutableMap.put("Сергей", 40);
        mutableMap.put("Елена", 33);

        ImmutableMap<String, Integer> copyFromMutable = ImmutableMap.copyOf(mutableMap);
        System.out.println("\nImmutableMap из обычной карты: " + copyFromMutable);

        // Builder для создания больших карт
        ImmutableMap<String, Integer> builtMap = ImmutableMap.<String, Integer>builder()
                .put("key1", 1)
                .put("key2", 2)
                .put("key3", 3)
                .put("key4", 4)
                .put("key5", 5)
                .build();
        System.out.println("ImmutableMap через Builder: " + builtMap);
    }

    // ============ 3. Preconditions ============
    private static void demonstratePreconditions() {
        System.out.println("--- 3. Preconditions ---");

        // Примеры с checkNotNull
        System.out.println("\n3.1. checkNotNull:");
        String validString = "Hello";
        String nullString = null;

        try {
            String result = checkNotNull(validString, "Строка не должна быть null");
            System.out.println("   ✅ checkNotNull прошел: " + result);
        } catch (NullPointerException e) {
            System.out.println("   ❌ Ошибка: " + e.getMessage());
        }

        try {
            String result = checkNotNull(nullString, "Строка не должна быть null");
            System.out.println("   ✅ checkNotNull прошел: " + result);
        } catch (NullPointerException e) {
            System.out.println("   ✅ Поймано NullPointerException: " + e.getMessage());
        }

        // Примеры с checkArgument
        System.out.println("\n3.2. checkArgument:");

        // Корректный возраст
        try {
            checkArgument(30 >= 0 && 30 <= 120, "Возраст вне диапазона: %s", 30);
            System.out.println("   ✅ Возраст 30 корректен");
        } catch (IllegalArgumentException e) {
            System.out.println("   ❌ Ошибка: " + e.getMessage());
        }

        // Некорректный возраст (слишком большой)
        try {
            checkArgument(200 >= 0 && 200 <= 120, "Возраст вне диапазона: %s", 200);
            System.out.println("   ✅ Возраст 200 корректен");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✅ Поймано IllegalArgumentException: " + e.getMessage());
        }

        // Некорректный возраст (отрицательный)
        try {
            checkArgument(-5 >= 0 && -5 <= 120, "Возраст вне диапазона: %s", -5);
            System.out.println("   ✅ Возраст -5 корректен");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✅ Поймано IllegalArgumentException: " + e.getMessage());
        }

        // Проверка строки на пустоту
        System.out.println("\n3.3. Проверка строк:");
        String emptyString = "";
        String nonEmptyString = "Иван";

        try {
            checkArgument(!emptyString.trim().isEmpty(), "Имя не должно быть пустым");
            System.out.println("   ✅ Строка не пуста");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✅ Поймано исключение: " + e.getMessage());
        }

        try {
            checkArgument(!nonEmptyString.trim().isEmpty(), "Имя не должно быть пустым");
            System.out.println("   ✅ Строка не пуста: " + nonEmptyString);
        } catch (IllegalArgumentException e) {
            System.out.println("   ❌ Ошибка: " + e.getMessage());
        }
    }

    // ============ 4. Регистрация пользователей ============
    private static void demonstrateRegistration() {
        System.out.println("--- 4. Регистрация пользователей ---\n");

        // Корректная регистрация
        System.out.println("Корректные данные:");
        registerUser("Иван", 30);
        registerUser("Мария", 25);
        registerUser("Петр", 45);

        System.out.println("\nНекорректные данные:");

        // Пустое имя
        registerUser("", 20);

        // Имя с пробелами
        registerUser("   ", 30);

        // Слишком молодой
        registerUser("Анна", -5);

        // Слишком старый
        registerUser("Сергей", 150);

        // null имя
        registerUser(null, 30);

        System.out.println("\nИтоговый список зарегистрированных пользователей:");
        if (registeredUsers.isEmpty()) {
            System.out.println("   Нет зарегистрированных пользователей");
        } else {
            for (String user : registeredUsers) {
                System.out.println("   " + user);
            }
        }
        System.out.println("   Всего: " + registeredUsers.size());
    }

    // ============ Метод регистрации с Preconditions ============
    public static void registerUser(String name, int age) {
        try {
            System.out.println("\nПопытка регистрации: " + name + ", " + age);

            // Проверяем, что имя не null и не пустое
            checkNotNull(name, "Имя не должно быть null");
            checkArgument(!name.trim().isEmpty(), "Имя не должно быть пустым или состоять только из пробелов");
            checkArgument(name.length() >= 2, "Имя должно содержать минимум 2 символа");

            // Проверяем возраст
            checkArgument(age >= 0 && age <= 120,
                    "Возраст должен быть в диапазоне 0..120, а не %s", age);

            // Если все проверки пройдены, регистрируем пользователя
            String userInfo = name + " (" + age + " лет)";
            registeredUsers.add(userInfo);
            System.out.println("   ✅ Зарегистрирован: " + userInfo);

        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("   ❌ Ошибка регистрации: " + e.getMessage());
        }
    }
}
