# Модуль 34. Тестирование: JUnit + Mockito

Базовый JUnit разобран в [модуле 28](../m28_junit/theory.md). Здесь — **Mockito**: библиотека для создания «заглушек» (моков) зависимостей, чтобы тестировать класс **в изоляции**.

> ⚠️ Задачи требуют зависимостей **JUnit 5** и **Mockito**. Запускайте в IDE или через Maven/Gradle.

## Зависимости (Maven)

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>
```

---

## Зачем нужны моки

Класс `OrderService` зависит от `PaymentGateway` и `Repository`. Чтобы протестировать **логику OrderService**, не нужны настоящие платежи и БД — их заменяют **моками** (управляемыми подделками).

```
       реальный                          в тесте
  OrderService                       OrderService
      │ зависит от                        │ зависит от
      ▼                                    ▼
  PaymentGateway (реальный API)   PaymentGateway (МОК — мы задаём ответы)
  Repository     (реальная БД)    Repository     (МОК)
```

| Без моков | С моками |
|-----------|----------|
| тест зависит от БД, сети | тест изолирован и быстр |
| трудно воспроизвести ошибки | мок легко «сломать» по сценарию |
| медленно, нестабильно | мгновенно, детерминированно |

---

## Создание мока

```java
import static org.mockito.Mockito.*;

PaymentGateway gateway = mock(PaymentGateway.class);   // подделка
```

### Настройка поведения: `when().thenReturn()`

```java
when(gateway.charge(100.0)).thenReturn(true);   // при charge(100) вернёт true
when(gateway.charge(5000.0)).thenReturn(false);

// бросить исключение:
when(gateway.charge(-1)).thenThrow(new IllegalArgumentException());
```

### Проверка вызовов: `verify()`

Mockito помнит, что вызывалось у мока:

```java
service.placeOrder(100.0);

verify(gateway).charge(100.0);              // был ли вызван charge(100)?
verify(gateway, times(1)).charge(100.0);    // ровно 1 раз
verify(gateway, never()).refund(anyDouble()); // ни разу
verifyNoInteractions(repository);           // вообще не трогали
```

---

## Аннотации Mockito (с JUnit 5)

```java
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock PaymentGateway gateway;        // создаст мок автоматически
    @Mock OrderRepository repository;

    @InjectMocks OrderService service;   // создаст service и подставит моки

    @Test
    void testPlaceOrder() {
        when(gateway.charge(100.0)).thenReturn(true);
        boolean ok = service.placeOrder(100.0);
        assertTrue(ok);
        verify(repository).save(any());
    }
}
```

| Аннотация | Назначение |
|-----------|-----------|
| `@Mock` | создать мок зависимости |
| `@InjectMocks` | создать тестируемый объект и внедрить в него моки |
| `@ExtendWith(MockitoExtension.class)` | включить поддержку аннотаций Mockito |

---

## Матчеры аргументов

Когда точное значение неважно:

```java
when(repository.findById(anyLong())).thenReturn(order);
verify(gateway).charge(anyDouble());
verify(service).process(eq("ключ"), anyInt());   // смешивать eq() и any()
```

> ⚠️ Нельзя смешивать «сырые» значения и матчеры: если хоть один аргумент — матчер, все должны быть матчерами (используйте `eq(...)`).

---

## ArgumentCaptor — захват аргументов

Позволяет проверить, **с чем именно** вызвали метод:

```java
ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
verify(repository).save(captor.capture());
Order saved = captor.getValue();
assertEquals("NEW", saved.getStatus());
```

---

## `spy` — частичный мок

`spy` оборачивает **реальный** объект: методы работают по-настоящему, но отдельные можно переопределить.

```java
List<String> spyList = spy(new ArrayList<>());
spyList.add("a");                       // реальное добавление
when(spyList.size()).thenReturn(100);   // но size() подменён
```

---

## Mock vs Stub vs Spy

| Термин | Что это |
|--------|---------|
| **Mock** | полная подделка; проверяем взаимодействие (verify) |
| **Stub** | возвращает заданные данные (when/thenReturn) |
| **Spy** | реальный объект с частично подменённым поведением |

---

## Хорошие практики

- Мокайте **зависимости**, а не тестируемый класс.
- Не мокайте то, чем не владеете (внешние API оборачивайте).
- Один тест проверяет одно поведение.
- `verify` — для проверки **взаимодействий**, `assert` — для проверки **результата**.

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| смешение `eq()` и сырых значений | все аргументы должны быть матчерами |
| мок тестируемого класса | тестируйте реальный объект, мокайте зависимости |
| `when` на void-методе | используйте `doThrow(...).when(mock).method()` |
| избыточные `verify` | проверяйте только значимые взаимодействия |
| `UnnecessaryStubbingException` | настроили `when`, который не используется |

## Дополнительные источники

- Mockito docs — javadoc.io/doc/org.mockito.
- «Practical Unit Testing with JUnit and Mockito» (Tomek Kaczanowski).

## Что дальше

В [модуле 35](../m35_logging/theory.md) — логирование.
