# Тема 10. Strategy и Command

Первые **поведенческие** паттерны GoF. Они описывают, как объекты взаимодействуют и распределяют обязанности. **Strategy** делает алгоритм взаимозаменяемым, **Command** превращает запрос в объект (с очередью, отменой, логом).

```
Поведенческие паттерны (взаимодействие объектов)
  Strategy — семейство взаимозаменяемых алгоритмов
  Command  — запрос как объект (вызов, очередь, undo)
  Observer, Chain of Responsibility   (тема 11)
  State, Template Method              (тема 12)
  Iterator, Visitor                   (тема 13)
```

---

## 1. Strategy — взаимозаменяемый алгоритм

Выносит алгоритм в отдельный объект за интерфейсом; контекст хранит ссылку на стратегию и делегирует ей работу. Алгоритм меняется во время выполнения, без `if/switch`.

```java
interface DiscountStrategy { long apply(long priceCents); }

class NoDiscount      implements DiscountStrategy { public long apply(long p){ return p; } }
class PercentDiscount implements DiscountStrategy {
    private final int pct;
    PercentDiscount(int pct){ this.pct = pct; }
    public long apply(long p){ return p - p*pct/100; }
}

class Checkout {                                  // контекст
    private DiscountStrategy strategy;
    void setStrategy(DiscountStrategy s){ this.strategy = s; }   // меняем в рантайме
    long total(long price){ return strategy.apply(price); }
}
```

```
   Checkout --хранит--▶ DiscountStrategy
                          + NoDiscount
                          + PercentDiscount   (подставляется любая)
```

### Strategy через лямбду

Если у интерфейса один метод (функциональный), стратегию задают лямбдой:

```java
DiscountStrategy half = price -> price / 2;       // лёгкая стратегия без класса
checkout.setStrategy(half);
```

Strategy — это «движок» принципа OCP (тема 02): новый алгоритм = новая реализация интерфейса.

> Где встречается: `Comparator` в `sort(...)`, `Collections.sort(list, byName)`; политики в Spring; стратегии расчётов в бизнес-логике.

---

## 2. Command — запрос как объект

Инкапсулирует запрос (действие + параметры) в объект с методом `execute()`. Это позволяет: складывать команды в очередь, логировать, повторять и **отменять** (`undo()`).

```java
interface Command { void execute(); void undo(); }

class AddTextCommand implements Command {
    private final StringBuilder doc;
    private final String text;
    AddTextCommand(StringBuilder doc, String text){ this.doc = doc; this.text = text; }
    public void execute(){ doc.append(text); }
    public void undo()   { doc.setLength(doc.length() - text.length()); }  // обратное действие
}

class Invoker {                                   // вызывающий: хранит историю
    private final Deque<Command> history = new ArrayDeque<>();
    void run(Command c){ c.execute(); history.push(c); }
    void undoLast(){ if (!history.isEmpty()) history.pop().undo(); }
}
```

```
   Клиент --создаёт--▶ Command --кладёт--▶ Invoker
                                  execute() / хранит для undo()
```

Каждая команда знает, **как сделать** и **как откатить** своё действие. Invoker не знает деталей — только дёргает `execute()`/`undo()`.

> Где встречается: операции undo/redo в редакторах; очереди задач; `Runnable` как простейшая команда; CQRS-команды (тема 20).

---

## Strategy vs Command

| | Strategy | Command |
|---|----------|---------|
| Инкапсулирует | алгоритм («как вычислить») | действие/запрос («что сделать») |
| Главный метод | один (apply/compute) | execute() (+ часто undo()) |
| Зачем | подменять алгоритм | очередь, лог, отмена, отложенный запуск |
| Хранит историю | обычно нет | да (для undo/redo) |

Оба прячут «что именно делать» за интерфейсом — но Strategy про **вычисление**, Command про **действие во времени**.

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Strategy с состоянием контекста | стратегия лезет в поля контекста | передавай нужные данные в метод-аргументы |
| `if (type)` вместо смены стратегии | ветвление вместо полиморфизма | подставляй реализацию стратегии |
| Command без симметричного undo() | откат ломает состояние | undo точно обращает execute |
| «Жирная» команда с бизнес-логикой | команда делает слишком много | команда вызывает сервис/получателя, а не реализует всё сама |
| Strategy там, где хватит лямбды | лишние классы на одну строку | функциональный интерфейс + лямбда |

---

## 🔗 Связь с другими темами

- **OCP (тема 02)** — Strategy её прямая реализация.
- **Factory (тема 04)** — часто создаёт нужную стратегию/команду.
- **State (тема 12)** — структурно похож на Strategy, но переключает поведение сам объект по своему состоянию.
- **CQRS (тема 20)** — Command как основа разделения команд и запросов.

## ➡️ Что дальше

Тема 11 — **Observer и Chain of Responsibility**: как уведомлять подписчиков об изменениях и как пропускать запрос по цепочке обработчиков, пока кто-то его не обработает.
