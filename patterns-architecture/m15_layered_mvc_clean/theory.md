# Тема 15. Layered, MVC, Clean Architecture

GRASP (тема 14) подсказывает, какому **классу** дать обязанность. Эта тема — масштабом выше: как сгруппировать классы в **слои**, чтобы система делилась на понятные уровни с контролируемыми зависимостями.

```
Layered        — горизонтальные слои: Presentation → Business → Persistence
MVC            — Model / View / Controller (разделение данных, отображения, управления)
Clean (Onion)  — концентрические слои; зависимости направлены ВНУТРЬ, к домену
```

---

## 1. Layered (многослойная) архитектура

Классическое деление на три слоя; каждый зависит только от слоя **ниже**:

```
   Presentation (Controller)   ← принимает запрос (UI/API)
        | зависит от
   Business (Service)          ← бизнес-логика, сценарии
        | зависит от
   Persistence (Repository)    ← доступ к данным
```

```java
class ClientController {                 // Presentation
    private final ClientService service;
    ClientController(ClientService s){ this.service = s; }
    public String show(String id){ return service.describe(id); }
}
class ClientService {                    // Business
    private final ClientRepository repo;
    ClientService(ClientRepository r){ this.repo = r; }
    public String describe(String id){ ... repo.findById(id) ... }
}
interface ClientRepository {             // Persistence
    Client findById(String id);
}
```

**Правило:** вызовы идут сверху вниз (Presentation → Business → Persistence), не наоборот. Контроллер не лезет напрямую в репозиторий, минуя сервис.

> Это слоистое разбиение лежит в основе типичного Spring-приложения (`@Controller` → `@Service` → `@Repository`).

---

## 2. MVC — Model, View, Controller

Разделяет три обязанности UI-приложения:

| Часть | Отвечает за | Пример |
|-------|-------------|--------|
| **Model** | данные и правила | `Counter { int value; increment(); }` |
| **View** | отображение | печатает значение на экран |
| **Controller** | обработку ввода, связь View и Model | по команде «+» зовёт `model.increment()` и просит View перерисовать |

```java
class CounterModel { private int value; void inc(){ value++; } int value(){ return value; } }
class CounterView  { void render(int v){ System.out.println("Счёт: " + v); } }
class CounterController {
    private final CounterModel model; private final CounterView view;
    CounterController(CounterModel m, CounterView v){ this.model = m; this.view = v; }
    void onIncrement(){ model.inc(); view.render(model.value()); }   // ввод → модель → отображение
}
```

```
   Ввод --▶ Controller --изменяет--▶ Model
                  |                    |
                  +--просит показать--▶ View --читает--▶ Model
```

Цель — чтобы View не содержал логику, Model не знал об отображении, а Controller их связывал.

---

## 3. Clean Architecture (она же Onion / Hexagonal-родственник)

Слои концентрические, и есть **одно жёсткое правило**:

> **Dependency Rule:** зависимости в исходном коде направлены только **внутрь** — к домену. Внутренние слои **ничего не знают** о внешних.

```
        +-------------------------------------+
        |  Frameworks & Drivers (БД, Web, UI)  |  внешний
        |   +-----------------------------+    |
        |   |  Interface Adapters         |    |
        |   |   +---------------------+   |    |
        |   |   |  Use Cases          |   |    |
        |   |   |   +-------------+   |   |    |
        |   |   |   |  Entities   |   |   |    |  внутренний (домен)
        |   |   |   +-------------+   |   |    |
        |   |   +---------------------+   |    |
        |   +-----------------------------+    |
        +-------------------------------------+
        зависимости ---------------▶ внутрь
```

- **Entities** — доменные сущности и правила; не зависят ни от чего.
- **Use Cases** — сценарии приложения; зависят от Entities, не от БД/Web.
- **Adapters / Frameworks** — БД, Web, UI; зависят от внутренних слоёв.

Чтобы Use Case не зависел от БД, он работает через **интерфейс-порт** (gateway), который реализует внешний слой (это DIP, тема 03, в масштабе архитектуры):

```java
// внутри (use case) — определяет, ЧТО ему нужно
interface AccountGateway { Account load(String id); }

class GetBalanceUseCase {
    private final AccountGateway gateway;            // зависит на абстракцию (внутрь)
    GetBalanceUseCase(AccountGateway g){ this.gateway = g; }
    long balance(String id){ return gateway.load(id).balance(); }
}
// снаружи (адаптер БД) — реализует порт; зависит внутрь, на интерфейс
class JdbcAccountGateway implements AccountGateway { ... }
```

Тема 17 (Hexagonal) — практическое продолжение этой идеи (порты и адаптеры).

---

## Сравнение

| | Layered | MVC | Clean |
|---|---------|-----|-------|
| Деление | горизонтальные слои | роли в UI | концентрические кольца |
| Зависимости | сверху вниз | Controller связывает M и V | строго внутрь, к домену |
| Где домен | средний слой (Business) | Model | центр (Entities) |
| Главная цель | разделить уровни | отделить отображение | защитить домен от инфраструктуры |

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Контроллер лезет в репозиторий | пропущен слой Business | Presentation → Business → Persistence, без перескоков |
| Domain знает о БД/Web | `Entity` импортирует JDBC/HTTP | зависимости только внутрь (Dependency Rule) |
| «Жирный» контроллер | бизнес-логика в Presentation | логика в Service/Use Case |
| View с логикой | расчёты/правила во View | View только отображает |
| Анемичные слои-обёртки | сервис лишь делегирует репозиторий 1-в-1 | слой нужен, когда добавляет ценность; иначе не плоди |
| Утечка доменных объектов наружу | сущность БД уходит в API как есть | разделяй domain и DTO (см. задачи) |

---

## 🔗 Связь с другими темами

- **GRASP Controller (тема 14)** — это слой Presentation.
- **DIP (тема 03)** — фундамент Dependency Rule в Clean.
- **Hexagonal (тема 17)** — прямое развитие Clean: порты и адаптеры.
- **DDD (темы 18–19)** — наполняет средний/внутренний слой богатой доменной моделью.

## ➡️ Что дальше

Тема 16 — **DI и IoC**: как зависимости между слоями не «прибивать» через `new`, а внедрять извне; ручное внедрение, контейнеры, Service Locator. Это механика, которая склеивает слои из этой темы.
