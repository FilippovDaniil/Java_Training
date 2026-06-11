# Модуль 35. Логирование

**Логирование** — запись событий программы (информация, предупреждения, ошибки) в журнал. В отличие от `System.out.println`, логи имеют уровни, метки времени, источник и настраиваемый вывод.

> ⚠️ Задачи требуют зависимостей **SLF4J + Logback** (или Log4j2). Добавьте их через Maven/Gradle.

## Почему не `System.out.println`

| `System.out.println` | Логирование |
|----------------------|-------------|
| нельзя отключить без правки кода | уровни включаются/выключаются конфигом |
| нет времени, потока, класса | автоматические метаданные |
| только в консоль | файл, консоль, сеть, БД (appenders) |
| засоряет код в проде | управляется централизованно |

---

## SLF4J — фасад логирования

**SLF4J** (Simple Logging Facade for Java) — это **интерфейс**, а конкретную реализацию (Logback, Log4j2) подключают отдельно. Код пишут против SLF4J — реализацию можно менять, не трогая код.

```
   Ваш код ──▶ SLF4J (фасад/API) ──▶ Logback / Log4j2 (реализация)
```

### Зависимости (Maven, вариант Logback)

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.13</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.5.6</version>
</dependency>
```

---

## Создание и использование логгера

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public void process(int id) {
        log.info("Обработка заказа {}", id);
        try {
            // ...
        } catch (Exception e) {
            log.error("Ошибка при обработке заказа {}", id, e);
        }
    }
}
```

> Логгер делают `private static final` и привязывают к классу (`getLogger(Класс.class)`) — так в логах виден источник.

---

## Уровни логирования

От самого детального к самому важному:

```
TRACE  <  DEBUG  <  INFO  <  WARN  <  ERROR
(шумно)                              (только проблемы)
```

| Уровень | Когда использовать |
|---------|--------------------|
| `TRACE` | максимально детальная отладка |
| `DEBUG` | отладочная информация для разработчика |
| `INFO` | важные бизнес-события (заказ создан) |
| `WARN` | подозрительные ситуации, не ошибки |
| `ERROR` | ошибки, требующие внимания |

В конфигурации задают **порог**: например, уровень `INFO` пропустит INFO/WARN/ERROR и отбросит DEBUG/TRACE.

```java
log.trace("Очень детально");
log.debug("Значение x = {}", x);
log.info("Пользователь {} вошёл", user);
log.warn("Память на исходе: {}%", percent);
log.error("Не удалось сохранить", exception);
```

---

## Параметризованные сообщения `{}`

```java
// ✅ ПРАВИЛЬНО — подстановка ленивая, без конкатенации
log.info("Заказ {} на сумму {}", orderId, amount);

// ❌ ПЛОХО — строка склеивается всегда, даже если INFO отключён
log.info("Заказ " + orderId + " на сумму " + amount);
```

> `{}` подставляет аргументы только если уровень включён — это экономит ресурсы.

---

## Логирование исключений

```java
try {
    riskyOperation();
} catch (IOException e) {
    log.error("Сбой операции с файлом {}", fileName, e);   // исключение — ПОСЛЕДНИЙ аргумент
}
```

> Передавайте исключение **отдельным** (последним) аргументом, без `{}` — логгер выведет полный стектрейс.

---

## Конфигурация (Logback — `logback.xml`)

Файл `src/main/resources/logback.xml`:

```xml
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>app.log</file>
        <encoder>
            <pattern>%d %-5level %logger - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>

    <!-- отдельный уровень для пакета -->
    <logger name="com.example.debugzone" level="debug"/>
</configuration>
```

### Appender — куда писать

| Appender | Назначение |
|----------|-----------|
| `ConsoleAppender` | в консоль |
| `FileAppender` | в файл |
| `RollingFileAppender` | в файл с ротацией (по размеру/дате) |

---

## MDC — контекст в логах

MDC (Mapped Diagnostic Context) добавляет контекст (например, ID запроса) во все логи потока:

```java
import org.slf4j.MDC;

MDC.put("requestId", "abc-123");
log.info("Начало обработки");     // в логе будет requestId=abc-123
MDC.clear();
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| конкатенация строк вместо `{}` | строка строится даже при выключенном уровне |
| `System.out.println` в проде | не управляется, нет метаданных |
| логирование паролей/токенов | утечка чувствительных данных |
| `e.printStackTrace()` вместо `log.error(..., e)` | трассировка уходит мимо журнала |
| избыточный `INFO` | «шум», в котором теряются важные события |

## Дополнительные источники

- SLF4J manual — slf4j.org/manual.html.
- Logback documentation — logback.qos.ch.

## Что дальше

В [модуле 36](../m36_network_basics/theory.md) — устройство сети (теория).
