# Модуль 101. Spring Test: инструменты тест-стека

Блок Spring Security закрыт ([модули 93–100](../m100_spring_security_testing/theory.md)). Начинаем системный разговор о **тестировании**. Прежде чем поднимать Spring-контекст, нужно владеть базовым инструментарием, который входит в `spring-boot-starter-test`: **JUnit 5** (движок), **AssertJ** и **Hamcrest** (проверки), **JSONassert** (сравнение JSON), **Mockito** (моки). Этот модуль — обзор каждого с примерами.

> Практика — задачи в `practice/`. Это **тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимость одна: `org.springframework.boot:spring-boot-starter-test` (тянет JUnit 5, AssertJ, Hamcrest, JSONassert, Mockito, JsonPath). bare-javac не верифицируется (норма). Сквозной проект — **Task Tracker API**.

---

## Что входит в `spring-boot-starter-test`

```
   spring-boot-starter-test
   ├── JUnit 5 (Jupiter)   — движок: @Test, lifecycle, параметризация
   ├── AssertJ             — текучие проверки: assertThat(x).isEqualTo(...)
   ├── Hamcrest            — матчеры: assertThat(x, is(...))
   ├── Mockito             — моки и стабы коллабораторов
   ├── JSONassert          — сравнение JSON-строк (с допусками)
   ├── JsonPath            — выборка по JSON ($.field)
   └── Spring Test         — MockMvc, @SpringBootTest и т.д. (модули 104+)
```

---

## JUnit 5: основа

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("сумма двух положительных")            // человекочитаемое имя
    void adds_two_numbers() {
        assertEquals(5, 2 + 3);                          // ожидаемое, фактическое
        assertTrue(5 > 0);
        assertNotNull("x");
    }

    @Test
    void throws_on_division_by_zero() {
        assertThrows(ArithmeticException.class, () -> { int x = 1 / 0; });
    }
}
```

| Аннотация / метод | Назначение |
|-------------------|------------|
| `@Test` | тестовый метод |
| `@DisplayName("...")` | читаемое имя в отчёте |
| `@Disabled` | временно отключить |
| `assertEquals/assertTrue/assertNull` | базовые проверки |
| `assertThrows(Ex.class, executable)` | проверка исключения |
| `assertAll(...)` | сгруппировать проверки (все выполнятся) |

### Lifecycle и `@Nested`

```java
@BeforeAll  static void initAll() {}     // один раз до всех (static)
@BeforeEach void init() {}               // перед каждым тестом
@AfterEach  void tearDown() {}           // после каждого
@AfterAll   static void cleanup() {}     // один раз после всех

@Nested class WhenEmpty { @Test void ... }   // группировка связанных тестов
```

### Параметризованные тесты

```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void positive(int n) { assertTrue(n > 0); }

@ParameterizedTest
@CsvSource({"NEW,true", "DONE,false"})
void canEdit(String status, boolean expected) { /* ... */ }

@ParameterizedTest
@MethodSource("titles")
void notBlank(String t) { assertFalse(t.isBlank()); }
static Stream<String> titles() { return Stream.of("a", "b"); }
```

---

## AssertJ: текучие проверки (предпочтительно)

```java
import static org.assertj.core.api.Assertions.*;

assertThat(sum).isEqualTo(5);
assertThat(name).isNotNull().startsWith("Al").hasSize(5);     // цепочка
assertThat(list).hasSize(3).contains("a").containsExactly("a", "b", "c");
assertThat(task.getStatus()).isEqualTo(Status.NEW);

assertThat(tasks)                                              // извлечение полей
        .extracting("title")
        .containsExactly("Кофе", "Чай");

assertThatThrownBy(() -> service.find(99L))                    // исключение
        .isInstanceOf(NotFoundException.class)
        .hasMessageContaining("99");
```

AssertJ читается как фраза и даёт подробные сообщения об ошибке. **В курсе предпочитаем AssertJ** для проверок в теле теста.

---

## Hamcrest: матчеры

```java
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

assertThat(sum, is(5));
assertThat(list, hasItem("a"));
assertThat(list, hasSize(3));
assertThat(name, allOf(startsWith("Al"), endsWith("ce")));
```

> Hamcrest-матчеры особенно нужны в `MockMvc` (`jsonPath("$.x", is(...))`) — модуль [104](../m104_spring_test_webmvc/theory.md). Для обычных проверок AssertJ удобнее.

---

## JSONassert: сравнение JSON

```java
import org.skyscreamer.jsonassert.JSONAssert;

String expected = "{\"id\":1,\"title\":\"Кофе\"}";
String actual   = "{\"title\":\"Кофе\",\"id\":1,\"extra\":true}";

JSONAssert.assertEquals(expected, actual, false);   // false = LENIENT: порядок и лишние поля игнорируются
JSONAssert.assertEquals(expected, actual, true);    // true  = STRICT: точное совпадение (упадёт из-за "extra")
```

| Режим | Поведение |
|-------|-----------|
| `false` (LENIENT) | порядок полей не важен, лишние поля в `actual` допустимы |
| `true` (STRICT) | полное соответствие структуры и набора полей |

---

## Mockito: моки коллабораторов

```java
import static org.mockito.Mockito.*;

TaskRepository repo = mock(TaskRepository.class);
when(repo.findById(1L)).thenReturn(Optional.of(new Task(1L, "Кофе")));   // стаб
when(repo.existsById(99L)).thenReturn(false);
when(repo.save(any())).thenThrow(new RuntimeException("db down"));        // стаб исключения

TaskService service = new TaskService(repo);
service.find(1L);

verify(repo).findById(1L);            // вызван ровно раз
verify(repo, never()).deleteById(any());
verifyNoMoreInteractions(repo);
```

С аннотациями (без Spring-контекста):

```java
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock TaskRepository repo;            // мок
    @InjectMocks TaskService service;     // мок внедряется в сервис

    @Test
    void finds() {
        when(repo.findById(1L)).thenReturn(Optional.of(new Task(1L, "Кофе")));
        assertThat(service.find(1L).title()).isEqualTo("Кофе");
    }
}
```

> `@ExtendWith(MockitoExtension.class)` — это **не** Spring-контекст: тест быстрый, без поднятия приложения. Подробно — в [модуле 102](../m102_spring_test_unit/theory.md).

---

## Пирамида тестов (куда это ведёт)

```
        ╱╲          E2E / интеграционные (медленные, мало)   ← Testcontainers (модуль 109)
       ╱──╲         @SpringBootTest, @WebMvcTest, @DataJpaTest (модули 104–108)
      ╱────╲        Unit-тесты (быстрые, много)               ← этот блок (модули 101–102)
     ╱──────╲
```

Чем ниже — тем быстрее и многочисленнее тесты. Базовый инструментарий этого модуля — фундамент всех уровней.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Путают `assertEquals(expected, actual)` порядок | в JUnit первый аргумент — ожидаемое | помнить порядок; AssertJ (`assertThat(actual).isEqualTo(expected)`) однозначнее |
| `@BeforeAll`/`@AfterAll` не вызываются | они должны быть `static` (в default lifecycle) | сделать `static` или `@TestInstance(PER_CLASS)` |
| Два разных `assertThat` | AssertJ и Hamcrest имеют одноимённый метод | следить за импортом (org.assertj vs org.hamcrest) |
| JSON-тест падает из-за порядка полей | STRICT-режим | `JSONAssert.assertEquals(exp, act, false)` (LENIENT) |
| `when(...)` на «строгом» моке не используется | Mockito ругается на лишние стабы | убрать лишний стаб или `lenient()` |
| Mockito не мокает `final`/`static` | ограничение прокси | рефакторинг или `mockito-inline` (редко) |

---

## Дополнительные источники

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/).
- [AssertJ — Core assertions guide](https://assertj.github.io/doc/).
- [Mockito — javadoc/обзор](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html).
- [JSONassert](https://github.com/skyscreamer/JSONassert).
- [модуль 34](../m34_testing_junit_mockito/theory.md) — первое знакомство с JUnit + Mockito.

## Что дальше

В [модуле 102](../m102_spring_test_unit/theory.md) — **unit-тесты бизнес-логики без Spring-контекста**: `@ExtendWith(MockitoExtension)`, `@Mock`/`@InjectMocks`, `ArgumentCaptor`, проверка взаимодействий и краевых случаев. Это самый быстрый и многочисленный слой пирамиды.
