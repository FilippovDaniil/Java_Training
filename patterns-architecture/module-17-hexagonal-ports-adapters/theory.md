# Тема 17. Hexagonal Architecture (Ports & Adapters)

Гексагональная архитектура (она же **Ports & Adapters**, Алистер Кокбёрн) — практическое развитие Clean Architecture (тема 15). Идея: доменное **ядро** ничего не знает о внешнем мире (БД, Web, очереди), а общается с ним только через **порты** (интерфейсы), к которым подключаются **адаптеры**.

```
                 ┌─────────── Адаптеры (драйверы) ───────────┐
   REST / CLI / Тест ──▶ [ driving port ]                    │
                 │            ▼                               │
                 │      ┌──────────────┐                      │
                 │      │   ЯДРО        │  (домен + use cases) │
                 │      │  ничего не    │                      │
                 │      │  знает о      │                      │
                 │      └──────────────┘                      │
                 │            │  [ driven port ]               │
                 │            ▼                                │
                 │   БД / Email / Очередь  ◀── адаптеры        │
                 └────────────────────────────────────────────┘
        Зависимости всегда направлены ВНУТРЬ, к ядру.
```

> Реализуем на чистом Java: порты — интерфейсы, адаптеры — обычные классы (in-memory, консольные, тестовые). DI (тема 16) подключает адаптеры к ядру.

---

## 1. Порт — интерфейс на границе ядра

**Порт** — контракт, через который ядро общается с внешним миром. Бывает двух видов:

| Вид порта | Направление | Кто вызывает | Пример |
|-----------|-------------|--------------|--------|
| **Driving / primary** (входной) | снаружи → в ядро | driving-адаптер (REST, CLI, тест) | `TransferUseCase` — API сценария |
| **Driven / secondary** (выходной) | из ядра → наружу | ядро вызывает, адаптер реализует | `AccountRepository` — нужна ядру, реализует БД |

```java
// driven port (выходной): ЯДРО объявляет, что ему нужно от хранилища
interface AccountRepository {
    Account load(String id);
    void save(Account account);
}

// driving port (входной): API сценария, который ядро ПРЕДОСТАВЛЯЕТ наружу
interface TransferUseCase {
    void transfer(String fromId, String toId, long amount);
}
```

Ключевое: **оба порта определены внутри ядра**. Выходной порт ядро *использует*, входной — *реализует*.

---

## 2. Ядро — домен и сценарии

Ядро реализует входные порты, опираясь на выходные. Оно **не импортирует** ничего инфраструктурного (ни JDBC, ни Spring Web).

```java
class TransferService implements TransferUseCase {   // реализует входной порт
    private final AccountRepository accounts;         // зависит от выходного порта (абстракции)
    TransferService(AccountRepository accounts) { this.accounts = accounts; }

    public void transfer(String fromId, String toId, long amount) {
        Account from = accounts.load(fromId);
        Account to = accounts.load(toId);
        from.withdraw(amount);                        // доменная логика и инварианты
        to.deposit(amount);
        accounts.save(from);
        accounts.save(to);
    }
}
```

---

## 3. Адаптеры — связь с внешним миром

**Адаптер** соединяет порт с конкретной технологией.

```java
// driven-адаптер: реализует выходной порт (здесь — in-memory; в проде был бы JDBC)
class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> store = new HashMap<>();
    public Account load(String id) { return store.get(id); }
    public void save(Account a) { store.put(a.id(), a); }
}

// driving-адаптер: дёргает входной порт (здесь — «консоль»; в проде — REST-контроллер)
class ConsoleTransferAdapter {
    private final TransferUseCase useCase;
    ConsoleTransferAdapter(TransferUseCase useCase) { this.useCase = useCase; }
    void run(String from, String to, long amount) { useCase.transfer(from, to, amount); }
}
```

Сборка (composition root) — DI из темы 16:

```java
AccountRepository repo = new InMemoryAccountRepository();   // driven-адаптер
TransferUseCase core = new TransferService(repo);           // ядро + выходной порт
ConsoleTransferAdapter ui = new ConsoleTransferAdapter(core); // driving-адаптер
ui.run("A", "B", 1000);
```

---

## Главные выгоды

| Выгода | Почему |
|--------|--------|
| Независимость от БД/Web | ядро говорит с ними через порты; замена технологии = новый адаптер |
| Тестируемость | в тестах вместо БД — fake-адаптер, реализующий тот же порт |
| Чистый домен | бизнес-логика не «загрязнена» инфраструктурным кодом |
| Симметрия | вход и выход устроены одинаково (порт + адаптер) |

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Ядро импортирует инфраструктуру | `import` JDBC/HTTP в домене | общение только через порты; зависимости внутрь |
| Порт описан «снаружи» | интерфейс репозитория лежит в слое БД | порт определяет ЯДРО (его потребитель) |
| Анемичное ядро | вся логика в адаптерах, ядро пустое | доменные правила — в ядре, адаптеры только «переводят» |
| Адаптер с бизнес-логикой | правила расползлись по адаптерам | адаптер только связывает технологию и порт |
| Слишком много портов «на всякий» | переусложнение | порт на реальную границу, а не на каждый класс |

---

## 🔗 Связь с другими темами

- **Clean Architecture (тема 15)** — гексагон это её практическая форма (Dependency Rule).
- **DIP (тема 03)** — порты это абстракции, заданные потребителем (ядром).
- **DI / IoC (тема 16)** — composition root подключает адаптеры к портам.
- **DDD (темы 18–19)** — ядро гексагона наполняется богатой доменной моделью.
- **Тестирование архитектуры (тема 25)** — ArchUnit стережёт правило «ядро не зависит от адаптеров».

## ➡️ Что дальше

Архитектурные стили (темы 14–17) заложены. Тема 18 — **стратегический DDD**: Bounded Context, Ubiquitous Language, Entity, Value Object, Aggregate — чем наполнить доменное ядро, чтобы оно отражало предметную область.
