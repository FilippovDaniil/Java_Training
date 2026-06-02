# Модуль 62. Внешняя конфигурация Spring: Environment, профили, ресурсы

> Практика — чистый Spring Context (spring-context 6.1.x), без Boot.
> Зависимость для Gradle: `implementation 'org.springframework:spring-context:6.1.14'`

Смотри также: [модуль 61 — Spring Core Advanced](../module-61-spring-core-advanced/theory.md)

---

## Зачем внешняя конфигурация

Жёстко прописанные значения в коде (URL БД, пароли, таймауты) делают приложение негибким.
Spring решает это через **абстракцию `Environment`** и набор источников свойств (`PropertySource`).

```
Источники свойств (приоритет — сверху вниз, выше = приоритетнее)
═══════════════════════════════════════════════════════════════════
  1. JVM-свойства (-Dkey=value, System.getProperties())
  2. Переменные среды (System.getenv())          ← только в Boot + AnnotationConfigEnv
  3. @PropertySource("classpath:override.properties")  ← последний объявленный
  4. @PropertySource("classpath:default.properties")   ← первый объявленный
  5. Значение по умолчанию в @Value("${key:DEFAULT}")
═══════════════════════════════════════════════════════════════════
         Environment.getProperty("key") читает из всех слоёв
```

---

## @PropertySource и @Value

`@PropertySource` подключает `.properties`-файл к `Environment` приложения.
`@Value("${ключ}")` внедряет значение прямо в поле или параметр.

```java
@Configuration
@PropertySource("classpath:app.properties")   // загружает файл в Environment
public class AppConfig {

    @Value("${app.name}")           // обязательное свойство
    private String appName;

    @Value("${app.timeout:30}")     // значение по умолчанию = 30
    private int timeout;

    @Bean
    public AppService appService() {
        return new AppService(appName, timeout);
    }
}
```

Файл `src/main/resources/app.properties`:
```properties
app.name=MyService
app.timeout=60
```

**Важно:** `@PropertySource` обрабатывается `@Configuration`-классом — размещайте аннотацию только там.

---

## Абстракция Environment

`Environment` — интерфейс для программного доступа к свойствам и профилям.

```java
@Autowired
private Environment env;

// Чтение свойств
String name    = env.getProperty("app.name");              // null если нет
String name2   = env.getProperty("app.name", "default");   // с дефолтом
int timeout    = env.getProperty("app.timeout", Integer.class, 30); // с типом

// Профили
String[] active = env.getActiveProfiles();   // ["dev"]
boolean  isDev  = env.acceptsProfiles(Profiles.of("dev"));
```

| Способ | Когда использовать |
|--------|--------------------|
| `@Value("${key}")` | простое внедрение в поле / параметр конструктора |
| `Environment.getProperty(key)` | программный доступ, динамические ключи |
| `@ConfigurationProperties` | группа свойств с одним префиксом (полноценно — модуль 65) |

---

## Профили (@Profile)

Профиль — именованный контекст окружения (`dev`, `prod`, `test`).
Бины и конфигурации, помеченные `@Profile`, создаются **только** при активном профиле.

```java
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("dev")                         // только в dev
    public DataSource devDataSource() {
        // H2 in-memory
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    @Profile("prod")                        // только в prod
    public DataSource prodDataSource() {
        // PostgreSQL (заглушка)
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:postgresql://db-host:5432/mydb");
        return ds;
    }
}
```

Активация профиля — три способа:

```java
// 1. Программно (до refresh)
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.getEnvironment().setActiveProfiles("dev");
ctx.register(AppConfig.class);
ctx.refresh();

// 2. JVM-флаг (в командной строке / Run Configuration)
// -Dspring.profiles.active=prod

// 3. Переменная среды
// SPRING_PROFILES_ACTIVE=prod
```

---

## SpEL в @Value

Spring Expression Language (SpEL) позволяет вычислять выражения прямо в аннотациях.

```java
// Значения из properties (${...})
@Value("${app.name:DefaultApp}")          // с дефолтом
private String appName;

// SpEL-выражения (#{...})
@Value("#{2 * 1024}")                     // вычисляется в 2048
private int bufferSize;

@Value("#{systemProperties['user.name']}") // системное свойство
private String osUser;

@Value("#{T(java.lang.Math).PI}")          // статическое поле класса
private double pi;

// Комбинирование
@Value("#{environment['app.name'] ?: 'Unnamed'}")  // из Environment + Elvis
private String name;
```

| Синтаксис | Что делает |
|-----------|------------|
| `${key}` | читает из источников свойств |
| `${key:default}` | читает, при отсутствии — дефолт |
| `#{выражение}` | вычисляет SpEL-выражение |
| `#{${key} * 2}` | смешанный: сначала `${}`, затем SpEL |

---

## Несколько источников и приоритет

```java
@Configuration
@PropertySource("classpath:default.properties")
@PropertySource("classpath:override.properties")   // объявлен ПОСЛЕДНИМ — приоритетнее
public class MultiSourceConfig { ... }
```

Если один и тот же ключ присутствует в обоих файлах — побеждает значение из `override.properties`.

**Про YAML:** в чистом Spring (без Boot) YAML-файлы не поддерживаются `@PropertySource` из коробки.
Нужен `YamlPropertySourceFactory`. Полноценная поддержка YAML — в Spring Boot (модуль 65).

---

## Работа с ресурсами

Spring предоставляет абстракцию `Resource` для работы с файлами независимо от их расположения.

```java
// Внедрение ресурса через @Value
@Value("classpath:banner.txt")
private Resource bannerResource;

// Чтение содержимого
String content = new String(bannerResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

// Другие префиксы:
// "file:/path/to/file.txt"    — файловая система
// "https://example.com/x.txt" — URL
// "classpath:config/app.xml"  — classpath (в jar/war)
```

| Реализация Resource | Когда создаётся |
|---------------------|-----------------|
| `ClassPathResource` | prefix `classpath:` |
| `FileSystemResource` | prefix `file:` |
| `UrlResource` | prefix `http:`, `https:`, `ftp:` |
| `ByteArrayResource` | из массива байт |

---

## Краткий обзор @ConfigurationProperties

`@ConfigurationProperties` — более мощная альтернатива набору `@Value`-полей:
группирует свойства с одним префиксом в один POJO-объект, поддерживает списки, вложенные объекты и валидацию.

```java
// application.properties:  mail.host=smtp.example.com  mail.port=587
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String host;
    private int port;
    // геттеры/сеттеры обязательны
}
```

> Полноценный разбор `@ConfigurationProperties` — в [модуле 65](../module-65-spring-boot-configuration/theory.md) (Spring Boot).

---

## Подводные камни

| Симптом | Причина | Решение |
|---------|---------|---------|
| `IllegalArgumentException: Could not resolve placeholder '${key}'` | Ключ не найден ни в одном источнике, нет дефолта | Добавить `${key:default}` или убедиться, что `.properties`-файл подключён через `@PropertySource` |
| `ConversionFailedException` / `TypeMismatchException` | Значение строки не конвертируется в целевой тип (например, `"abc"` в `int`) | Проверить значение в файле; добавить явный тип: `env.getProperty(key, Integer.class)` |
| Профильный бин не создаётся, `NoSuchBeanDefinitionException` | Профиль не активирован — ни через `-D`, ни программно | `ctx.getEnvironment().setActiveProfiles("dev")` **до** `ctx.refresh()` |
| `@PropertySource` не найден (FileNotFoundException) | Файл отсутствует в classpath или опечатка в пути | Проверить `src/main/resources`; добавить `ignoreResourceNotFound = true` для опциональных |
| YAML-файл игнорируется | `@PropertySource` не умеет YAML без `PropertySourceFactory` | Перейти на `.properties` или использовать `YamlPropertySourceFactory`; полностью — в Boot (модуль 65) |
| `@Profile` не работает на `@Bean`-методе | `@Profile` добавлен на конкретный класс, но класс не `@Configuration` / `@Component` | Убедиться, что класс отсканирован: `@ComponentScan` или явная регистрация |

---

## Дополнительные источники

- Официальная документация Spring: [docs.spring.io/spring-framework/reference/core/beans/environment.html](https://docs.spring.io/spring-framework/reference/core/beans/environment.html)
- «Spring in Action» (Craig Walls) — глава про профили и конфигурацию.
- JavaDoc: `org.springframework.core.env.Environment`, `org.springframework.context.annotation.PropertySource`.

## Что дальше

В [модуле 63](../module-63-spring-core-events-aop/theory.md) — события ApplicationContext и основы AOP.
