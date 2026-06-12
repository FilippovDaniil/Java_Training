# Модуль 66. Spring Boot в эксплуатации: логирование, Actuator, упаковка, тесты

Приложение из [модуля 65](../m65_spring_boot_web_config/theory.md) умеет принимать запросы. Чтобы довести его до **production-ready** состояния, нужно ещё четыре вещи: видеть, что внутри (логи), отдавать состояние наружу (Actuator), собирать в один артефакт (упаковка) и быть уверенным, что оно работает (тесты).

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-actuator`, `spring-boot-starter-test`. Bare-`javac` НЕ компилируется — запускайте в IDE/Gradle.

---

## Логирование

Spring Boot из коробки использует **SLF4J + Logback** (см. [модуль 35](../m35_logging/theory.md)). Получить логгер:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public void process(String id) {
        log.info("Обрабатываем заказ {}", id);   // {} — плейсхолдер, без конкатенации
        log.debug("Детали заказа {}", id);
        if (id == null) log.warn("id == null!");
    }
}
```

Уровни задаются в `application.properties`:

```properties
# корневой уровень
logging.level.root=INFO
# уровень для конкретного пакета
logging.level.com.example.shop=DEBUG
# вывод в файл
logging.file.name=logs/app.log
# формат строки
logging.pattern.console=%d{HH:mm:ss} %-5level %logger{36} - %msg%n
```

| Уровень | Когда |
|---------|-------|
| `ERROR` | сбой, требующий внимания |
| `WARN` | подозрительно, но работает |
| `INFO` | важные бизнес-события (старт, заказ создан) |
| `DEBUG` | детали для отладки |
| `TRACE` | максимально подробно |

> **Правило плейсхолдеров:** `log.debug("x = {}", x)`, а не `log.debug("x = " + x)`. При выключенном DEBUG конкатенация строки не выполняется — экономия.

---

## Actuator — мониторинг приложения

`spring-boot-starter-actuator` добавляет служебные HTTP-эндпоинты под `/actuator`.

```properties
# по умолчанию наружу открыт только /actuator/health — открываем больше:
management.endpoints.web.exposure.include=health,info,metrics
# детали health (для разработки):
management.endpoint.health.show-details=always
# наполнение /info:
info.app.name=Shop Service
info.app.version=1.0.0
```

| Эндпоинт | Что показывает |
|----------|----------------|
| `/actuator/health` | состояние (`UP`/`DOWN`) + проверки (БД, диск, ...) |
| `/actuator/info` | произвольная информация о приложении |
| `/actuator/metrics` | метрики (JVM, HTTP, пулы) |
| `/actuator/env` | свойства окружения |
| `/actuator/loggers` | уровни логирования (можно менять на лету) |
| `/actuator/beans` | все бины контекста |

### Кастомный индикатор здоровья

```java
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class WarehouseHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean ok = checkWarehouse();
        return ok ? Health.up().withDetail("items", 1500).build()
                  : Health.down().withDetail("reason", "склад недоступен").build();
    }
}
```

Этот индикатор автоматически попадёт в `/actuator/health`.

> **Ловушка из практики:** если K8s-проба бьёт в `/actuator/health`, а какой-то индикатор `DOWN` (например, mail) — проба падает (503) и под перезапускается. Отключить лишнее: `management.health.mail.enabled=false`.

---

## Упаковка и запуск

```
./gradlew bootJar                       # собрать исполняемый "fat jar"
java -jar build/libs/shop-1.0.0.jar     # запустить
java -jar shop.jar --server.port=9090   # переопределить свойство при запуске
java -jar shop.jar --spring.profiles.active=prod
```

**DevTools** (`spring-boot-devtools`) — для разработки: автоперезапуск при изменении кода, LiveReload. В production не включается.

> **Урок из практики (Docker):** Gradle-обёртка может качать дистрибутив внутри Docker-сборки и падать по таймауту. Надёжнее собрать jar на хосте, а в `Dockerfile` только копировать `build/libs/*.jar`.

---

## Тестирование Spring Boot

Два уровня тестов:

```
   @SpringBootTest                    @WebMvcTest(Controller.class)
   -----------------                  ------------------------------
   поднимает ВЕСЬ контекст            поднимает ТОЛЬКО web-слой
   (медленно, интеграционно)          (быстро, изолированно)
   проверяет связку модулей           проверяет один контроллер
```

### @SpringBootTest — интеграционный

```java
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    OrderService orderService;          // реальный бин из контекста

    @Test
    void processesOrder() {
        // TODO: вызвать сервис и проверить результат
    }
}
```

С `webEnvironment = RANDOM_PORT` поднимается реальный сервер, и можно бить по HTTP через `TestRestTemplate`.

### @WebMvcTest + MockMvc — slice-тест

```java
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean ProductService productService;   // зависимость контроллера — мок

    @Test
    void returnsProduct() throws Exception {
        mockMvc.perform(get("/api/products/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Мышь"));
    }
}
```

| Аннотация | Что поднимает | Скорость | Для чего |
|-----------|---------------|----------|----------|
| `@SpringBootTest` | весь контекст | медленно | интеграция, связки |
| `@WebMvcTest` | только MVC-слой | быстро | контроллеры (статусы, JSON) |
| `@DataJpaTest` | только JPA-слой | быстро | репозитории ([модуль 83](../m83_spring_data_jpa_testing/theory.md)) |
| `@MockBean` | подменяет бин моком | — | изолировать зависимости |

> Тонкости тестирования контроллеров под Spring Security (почему `@MockBean JwtAuthenticationFilter` ломает цепочку фильтров, когда нужен `@Import(SecurityConfig.class)`) разобраны в проектных инструкциях — раздел «SPRING SECURITY — ТЕСТИРОВАНИЕ».

---

## Чек-лист production-ready сервиса

```
[ ] Логи: уровни настроены, бизнес-события на INFO, плейсхолдеры {}
[ ] Actuator: /health открыт, лишние индикаторы выключены
[ ] /info заполнен (имя, версия)
[ ] Конфигурация вынесена в application.properties + профили (dev/prod)
[ ] Секреты НЕ в коде/git (env-переменные, Secret)
[ ] Тесты: @WebMvcTest на контроллеры + @SpringBootTest на ключевые сценарии
[ ] bootJar собирается, java -jar запускается
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `/actuator/health` отдаёт мало / 404 | эндпоинты не открыты | `management.endpoints.web.exposure.include=...` |
| Проба K8s падает (503) | health-индикатор `DOWN` | выключить лишний индикатор / починить зависимость |
| `@WebMvcTest` возвращает 200 пустой ответ | замокан сам фильтр (`doFilter` — `final`) | мокировать зависимости фильтра, не фильтр |
| `@WebMvcTest`: контроллер ждёт бин сервиса | сервис не в web-слое | объявить `@MockBean ServiceClass` |
| `@SpringBootTest` медленный | поднимает весь контекст | для проверки слоя брать `@WebMvcTest`/`@DataJpaTest` |
| Логи DEBUG засоряют prod | уровень не настроен | `logging.level...=INFO` для prod-профиля |

---

## Дополнительные источники

- [Spring Boot Actuator — документация](https://docs.spring.io/spring-boot/reference/actuator/index.html).
- [Testing in Spring Boot](https://docs.spring.io/spring-boot/reference/testing/index.html).
- [модуль 35](../m35_logging/theory.md) — SLF4J и Logback.
- [модуль 34](../m34_testing_junit_mockito/theory.md) — JUnit 5 и Mockito.

## Что дальше

Трек 54–66 завершён — пройден путь от инструментов сборки и HTTP до production-ready Spring Boot. В [модуле 67](../m67_spring_rest_http_backend/theory.md) начинается углублённый блок: **HTTP глазами backend-разработчика** — фундамент для профессионального проектирования REST-API.
