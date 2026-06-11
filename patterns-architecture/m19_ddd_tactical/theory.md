# Тема 19. Tactical DDD: Repository, сервисы, фабрики, события

Стратегический DDD (тема 18) дал строительные блоки (VO, Entity, Aggregate). **Тактический DDD** — инструменты вокруг них: как **хранить** агрегаты (Repository), куда девать логику **между** агрегатами (Domain Service), как **создавать** сложные агрегаты (Factory) и как фиксировать **факты** домена (Domain Events).

```
Repository     — коллекция агрегатов (load/save), скрывает хранилище
Domain Service — доменная логика, не принадлежащая одному агрегату
Factory        — сборка сложного агрегата в валидном состоянии
Domain Event   — «что-то значимое произошло в домене» (факт в прошедшем времени)
```

---

## 1. Repository — коллекция агрегатов

Repository выглядит как **коллекция** объектов домена, скрывая, где они на самом деле лежат (БД, память, файл). Интерфейс — в доменном языке (`findById`, `save`), реализация — в инфраструктуре (это выходной порт из темы 17).

```java
interface AccountRepository {              // контракт в терминах домена
    Account findById(String id);
    void save(Account account);
}
class InMemoryAccountRepository implements AccountRepository { ... }   // деталь
```

**Правило:** один Repository — на **агрегат** (на его корень), а не на каждую таблицу. Repository работает с целыми агрегатами, не с их кусочками.

---

## 2. Domain Service — логика «между» агрегатами

Если операция не принадлежит естественно ни одному агрегату (затрагивает несколько), её выносят в **доменный сервис**. Он содержит **доменную** логику (не техническую), оперирует агрегатами.

```java
class TransferService {                    // перевод затрагивает ДВА счёта — это не «дело» одного
    private final AccountRepository accounts;
    TransferService(AccountRepository accounts) { this.accounts = accounts; }
    void transfer(String fromId, String toId, Money amount) {
        Account from = accounts.findById(fromId);
        Account to   = accounts.findById(toId);
        from.withdraw(amount);             // правила внутри агрегатов
        to.deposit(amount);
        accounts.save(from); accounts.save(to);
    }
}
```

> Отличие от Application Service (тема 15, слой Business): доменный сервис содержит **бизнес-правила домена**, application-сервис **координирует** сценарий (транзакции, безопасность). На практике границу проводят по «есть ли тут доменное правило».

---

## 3. Factory — сборка агрегата

Когда создание агрегата сложное (много шагов, инварианты при рождении), его прячут в **фабрику** — чтобы агрегат всегда рождался валидным.

```java
class AccountFactory {
    Account openNew(String ownerId, Money initialDeposit) {
        Account account = new Account(generateId(), ownerId);
        if (initialDeposit.isPositive()) account.deposit(initialDeposit);  // правило при создании
        return account;                    // гарантированно валиден
    }
}
```

Фабрика — это Pure Fabrication (тема 14) + Creator (тема 14) в доменном масштабе. Иногда фабричный метод живёт прямо на агрегате (`Account.open(...)`).

---

## 4. Domain Event — факт домена

**Domain Event** — объект, фиксирующий, что **значимое произошло** (в прошедшем времени: `AccountOpened`, `MoneyDeposited`). Агрегат «записывает» события, а заинтересованные части системы на них реагируют (это Observer, тема 11, в доменном масштабе).

```java
// событие — неизменяемый факт
record MoneyDeposited(String accountId, long amountCents) {}

class Account {
    private final List<Object> events = new ArrayList<>();   // накопленные события
    void deposit(long amount) {
        this.balance += amount;
        events.add(new MoneyDeposited(id, amount));          // зафиксировали факт
    }
    List<Object> pullEvents() { var copy = List.copyOf(events); events.clear(); return copy; }
}
```

Дальше события **публикуют** (диспетчер раздаёт их обработчикам): отправить письмо, обновить проекцию, проинтегрироваться с другим контекстом.

```
   Account.deposit() ──записывает──▶ MoneyDeposited
                                          │ publish
                       ┌──────────────────┼──────────────────┐
                  EmailHandler       AuditHandler       ProjectionHandler
```

> Domain Events — мост к Event Sourcing/CQRS (тема 20) и к интеграции через Kafka (тема 24).

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Repository на каждую таблицу/сущность | репозитории для внутренностей агрегата | один репозиторий — на агрегат (корень) |
| Бизнес-логика утекла в сервис | агрегаты анемичны, всё в сервисах | правила — в агрегатах; в сервис только то, что между ними |
| Сервис делает работу одного агрегата | лишний сервис | если логика про один агрегат — она в нём (Information Expert) |
| Событие в настоящем времени (`Deposit`) | путаница команда/факт | событие — в прошедшем (`MoneyDeposited`); команда — в повелительном (`Deposit`) |
| Изменяемое событие | факт «переписали» | событие неизменяемо (record/final поля) |
| Публикация события до сохранения | подписчики видят то, чего ещё нет в БД | публикуй после успешного save (или транзакционно) |

---

## 🔗 Связь с другими темами

- **Aggregate (тема 18)** — Repository хранит агрегаты, события исходят из них.
- **Hexagonal (тема 17)** — Repository = выходной порт; реализация = driven-адаптер.
- **Observer (тема 11)** — Domain Events это Observer на уровне домена.
- **Event Sourcing / CQRS (тема 20)** — состояние как поток доменных событий.

## ➡️ Что дальше

Тема 20 — **Event Sourcing и CQRS**: что, если хранить не текущее состояние, а **поток событий**, и строить из него любые представления (проекции), разделив запись (команды) и чтение (запросы).
