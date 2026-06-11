# Модуль 28. Модульное тестирование с JUnit

**Модульное тестирование (unit testing)** — автоматическая проверка отдельных кусочков кода (методов, классов). **JUnit** — стандартная библиотека для тестов в Java. Здесь — JUnit 5 (Jupiter).

> ⚠️ Задачи этого модуля (`practice/`) — это **JUnit-тесты**, а не программы с `main`. Их нельзя запустить «голым» `javac`/`java` — нужна библиотека JUnit на classpath. Запускайте их **в IntelliJ IDEA** (▶ рядом с тестом) или через **Maven/Gradle** (`mvn test` / `gradle test`).

## Зачем нужны тесты

| Без тестов | С тестами |
|------------|-----------|
| правка ломает старое незаметно | тесты сразу покажут регрессию |
| проверка «руками» каждый раз | один клик — все проверки |
| страшно рефакторить | тесты — страховка |

---

## Подключение (Maven)

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

Тесты живут в `src/test/java` (см. [модуль 27](../m27_maven/theory.md)).

---

## Первый тест

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddition() {
        Calculator calc = new Calculator();   // 1. подготовка (Arrange)
        int result = calc.add(2, 3);           // 2. действие (Act)
        assertEquals(5, result);               // 3. проверка (Assert)
    }
}
```

- `@Test` — помечает метод как тест.
- Метод теста — `void`, без параметров (обычно).
- **Паттерн AAA**: Arrange (подготовить) → Act (выполнить) → Assert (проверить).

---

## Основные проверки (assertions)

```java
assertEquals(expected, actual);        // равны? (ожидаемое, фактическое)
assertNotEquals(a, b);                 // не равны
assertTrue(condition);                 // условие истинно
assertFalse(condition);                // условие ложно
assertNull(obj);                       // равно null
assertNotNull(obj);                    // не null
assertArrayEquals(arr1, arr2);         // массивы равны
assertSame(a, b);                      // тот же объект (==)

// с сообщением об ошибке:
assertEquals(5, result, "Сложение работает неверно");

// для double — с дельтой (погрешностью):
assertEquals(0.3, x, 0.0001);
```

> Порядок важен: `assertEquals(ожидаемое, фактическое)`. При несовпадении JUnit покажет оба значения.

---

## Проверка исключений: `assertThrows`

```java
@Test
void testDivisionByZero() {
    Calculator calc = new Calculator();
    assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
}
```

Лямбда внутри `assertThrows` **должна** бросить указанное исключение, иначе тест провалится.

---

## Группировка проверок: `assertAll`

Выполняет все проверки и сообщает обо всех провалившихся сразу:

```java
@Test
void testPerson() {
    Person p = new Person("Иван", 30);
    assertAll("person",
        () -> assertEquals("Иван", p.getName()),
        () -> assertEquals(30, p.getAge())
    );
}
```

---

## Жизненный цикл: `@BeforeEach`, `@AfterEach`

```java
class CalculatorTest {
    Calculator calc;

    @BeforeEach
    void setUp() {            // выполняется ПЕРЕД каждым тестом
        calc = new Calculator();
    }

    @AfterEach
    void tearDown() {         // выполняется ПОСЛЕ каждого теста
        // очистка ресурсов
    }

    @Test void test1() { ... }
    @Test void test2() { ... }   // calc создаётся заново для каждого
}
```

| Аннотация | Когда выполняется |
|-----------|-------------------|
| `@BeforeEach` | перед **каждым** тестом |
| `@AfterEach` | после **каждого** теста |
| `@BeforeAll` | один раз до всех (метод `static`) |
| `@AfterAll` | один раз после всех (метод `static`) |

---

## Полезные аннотации

```java
@DisplayName("Сложение двух положительных чисел")   // читаемое имя теста
@Test
void testAdd() { ... }

@Disabled("Временно отключён — баг #123")            // пропустить тест
@Test
void brokenTest() { ... }
```

---

## Параметризованные тесты

Один тест — много наборов данных, без дублирования:

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

@ParameterizedTest
@ValueSource(ints = {2, 4, 6, 100})
void testEven(int number) {
    assertTrue(number % 2 == 0);     // выполнится для каждого значения
}

@ParameterizedTest
@CsvSource({"2, 3, 5", "10, 5, 15", "0, 0, 0"})
void testAdd(int a, int b, int expected) {
    assertEquals(expected, new Calculator().add(a, b));
}
```

---

## Хороший тест

| Принцип | Значение |
|---------|----------|
| **Независимость** | тесты не зависят друг от друга и от порядка |
| **Один смысл** | один тест проверяет одну вещь |
| **Понятное имя** | `testWithdrawFailsWhenInsufficientFunds` |
| **Повторяемость** | один и тот же результат при каждом запуске |
| **Быстрота** | тесты выполняются мгновенно |

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `assertEquals(actual, expected)` | порядок: сначала ожидаемое |
| тесты зависят от порядка запуска | делайте независимыми, используйте `@BeforeEach` |
| сравнение `double` без дельты | используйте `assertEquals(a, b, delta)` |
| тест без assertion | ничего не проверяет — бесполезен |
| `@Test`-метод с `static`/`public`-требованиями | в JUnit 5 можно package-private, не static |

---

## Поздравляем!

Это последний модуль курса. Теперь у вас есть прочная база Core Java: от синтаксиса и ООП до коллекций, потоков, многопоточности и инструментов разработчика. Возвращайтесь к [README](../README.md) и продолжайте практиковаться!
