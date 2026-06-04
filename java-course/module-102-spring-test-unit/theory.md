# Модуль 102. Spring Test: unit-тесты бизнес-логики без Spring-контекста

Самый быстрый и многочисленный слой пирамиды — **unit-тесты**. Они проверяют один класс (обычно сервис) в полной изоляции: без БД, без HTTP, **без поднятия Spring-контекста**. Объект создаётся обычным `new`, его зависимости подменяются моками Mockito. В этом модуле — как писать такие тесты системно: `@ExtendWith(MockitoExtension)`, `@Mock`/`@InjectMocks`, `ArgumentCaptor`, проверка взаимодействий и краевых случаев.

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимость: `spring-boot-starter-test` (JUnit 5 + Mockito + AssertJ). bare-javac не верифицируется (норма). Сквозной проект — **Task Tracker API**.

---

## Почему без контекста

```
   @SpringBootTest (полный контекст)     unit-тест (@ExtendWith(MockitoExtension))
   ─────────────────────────────────     ──────────────────────────────────────
   поднимает приложение (~секунды)        просто new + моки (~миллисекунды)
   реальные бины, БД, веб                 всё внешнее — заглушки
   проверяет интеграцию                   проверяет ЛОГИКУ одного класса
   мало, на верхушке пирамиды             много, в основании пирамиды
```

Правило: **бизнес-логику проверяют unit-тестами**, а контекст/интеграцию — отдельными (медленными) тестами выше по пирамиде. Если для проверки логики приходится поднимать контекст — скорее всего, логику стоит выделить в чистый сервис.

---

## Каркас: `@ExtendWith(MockitoExtension)`

```java
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)        // активирует @Mock/@InjectMocks (это НЕ Spring!)
class TaskServiceTest {

    @Mock TaskRepository repo;             // зависимость — мок
    @Mock NotificationGateway gateway;     // ещё одна зависимость — мок
    @InjectMocks TaskService service;      // тестируемый объект; моки внедряются в конструктор

    @Test
    void ...() { /* given-when-then */ }
}
```

| Аннотация | Роль |
|-----------|------|
| `@ExtendWith(MockitoExtension.class)` | инициализирует моки, проверяет «строгие» стабы |
| `@Mock` | создать мок-зависимость |
| `@InjectMocks` | создать тестируемый объект и вставить в него моки |
| `@Spy` | частичный мок (реальные методы + выборочные стабы) — редко |

---

## Структура теста: given–when–then

```java
@Test
void completes_task_and_notifies() {
    // given — настраиваем моки
    Task task = new Task(1L, "Кофе", Status.NEW);
    when(repo.findById(1L)).thenReturn(Optional.of(task));
    when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

    // when — вызываем тестируемый метод
    Task result = service.complete(1L);

    // then — проверяем результат и взаимодействия
    assertThat(result.status()).isEqualTo(Status.DONE);
    verify(gateway).notifyCompleted(1L);
}
```

---

## Стабы: `thenReturn`, `thenThrow`, `thenAnswer`

```java
when(repo.findById(1L)).thenReturn(Optional.of(task));        // вернуть значение
when(repo.findById(99L)).thenReturn(Optional.empty());        // пусто
when(repo.save(any())).thenThrow(new DataAccessException(){}); // бросить исключение
when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0)); // вернуть переданный аргумент
```

> `thenAnswer(inv -> inv.getArgument(0))` удобен, когда `save` должен «вернуть то, что сохранили» (имитация присвоения id опускается или делается внутри answer).

---

## Проверка взаимодействий: `verify`

```java
verify(repo).save(task);                       // вызван ровно 1 раз с этим аргументом
verify(repo, times(2)).findById(any());        // ровно 2 раза
verify(gateway, never()).notifyCompleted(any());// не вызывался
verify(repo, atLeastOnce()).findAll();
verifyNoInteractions(gateway);                 // мок вообще не трогали
verifyNoMoreInteractions(repo);                // больше ничего, кроме уже проверенного
```

Unit-тест проверяет не только **что вернулось**, но и **как сервис общался с зависимостями** (сохранил? уведомил? не удалил лишнего?).

---

## `ArgumentCaptor`: что именно передали в мок

Когда важно проверить аргумент, переданный зависимости (например, какой объект ушёл в `save`):

```java
@Captor ArgumentCaptor<Task> captor;            // или ArgumentCaptor.forClass(Task.class)

@Test
void saves_task_with_NEW_status() {
    when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

    service.create("Кофе");

    verify(repo).save(captor.capture());        // перехватить аргумент
    Task saved = captor.getValue();
    assertThat(saved.title()).isEqualTo("Кофе");
    assertThat(saved.status()).isEqualTo(Status.NEW);   // логика выставила статус
}
```

---

## Краевые случаи и исключения

```java
@Test
void rejects_blank_title() {
    assertThatThrownBy(() -> service.create("  "))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("title");
    verifyNoInteractions(repo);                 // до репозитория дело не дошло
}

@Test
void find_missing_throws_not_found() {
    when(repo.findById(99L)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> service.find(99L)).isInstanceOf(NotFoundException.class);
}
```

Хороший unit-тест проверяет не только «счастливый путь», но и валидацию, пустые результаты, исключения.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `@Mock`/`@InjectMocks` == null | нет `@ExtendWith(MockitoExtension.class)` | добавить расширение |
| `@InjectMocks` не внедрил мок | нет подходящего конструктора/сеттера | конструктор-инъекция (рекомендуется) |
| `UnnecessaryStubbingException` | застабили то, что тест не использует | убрать лишний `when` или `lenient()` |
| Проверяют только результат, не поведение | забыли `verify` | проверять взаимодействия с зависимостями |
| Тестируют геттеры/тривиальный код | низкая ценность | тестировать логику, а не аксессоры |
| Подняли `@SpringBootTest` ради одного сервиса | избыточно и медленно | unit-тест с моками |
| Мок не сматчился (`null` вместо стаба) | аргумент не совпал (`eq` vs `any`) | согласовать матчеры (`any()`/`eq(...)`) |

---

## Дополнительные источники

- [Mockito — официальная документация](https://site.mockito.org/).
- [Baeldung: Mockito ArgumentCaptor](https://www.baeldung.com/mockito-argumentcaptor).
- [JUnit 5 + Mockito (`MockitoExtension`)](https://junit.org/junit5/docs/current/user-guide/#extensions).
- [модуль 101](../module-101-spring-test-basics/theory.md) — базовые инструменты (JUnit/AssertJ/Mockito).

## Что дальше

В [модуле 103](../module-103-spring-test-config/theory.md) — **управляемая конфигурация тестов**: профили (`@ActiveProfiles`), свойства (`@TestPropertySource`, `application-test.properties`), `@TestConfiguration` с тестовыми бинами и `@DynamicPropertySource`. Это мост от чистых unit-тестов к тестам, которым нужен (управляемый) контекст.
