# Тема 11. Observer и Chain of Responsibility

Ещё два поведенческих паттерна. **Observer** уведомляет подписчиков об изменении объекта. **Chain of Responsibility** пропускает запрос по цепочке обработчиков, пока кто-то его не обработает.

```
Observer                — «один меняется → многие узнают» (подписка на события)
Chain of Responsibility — запрос идёт по цепочке обработчиков до того, кто справится
```

---

## 1. Observer — наблюдатель

Объект-**субъект** хранит список подписчиков (**наблюдателей**) и при изменении автоматически их уведомляет. Субъект не знает конкретных классов наблюдателей — только интерфейс.

```java
interface Observer { void update(String event); }

class Subject {
    private final List<Observer> observers = new ArrayList<>();
    public void subscribe(Observer o)   { observers.add(o); }
    public void unsubscribe(Observer o) { observers.remove(o); }
    public void publish(String event) {
        for (Observer o : observers) o.update(event);   // уведомляем всех
    }
}

class EmailSubscriber implements Observer {
    public void update(String event) { System.out.println("email: " + event); }
}
```

```
   Subject --publish--▶ Observer1
            --publish--▶ Observer2
            --publish--▶ Observer3
   (подписчики добавляются/убираются динамически)
```

Слабая связанность: субъект и наблюдатели общаются только через интерфейс; можно добавлять/удалять подписчиков в рантайме.

> Где встречается: слушатели событий GUI; `PropertyChangeListener`; reactive-стримы; `@EventListener` в Spring; pub/sub.

---

## 2. Chain of Responsibility — цепочка обработчиков

Запрос проходит по цепочке обработчиков. Каждый либо обрабатывает его, либо передаёт **следующему**. Отправитель не знает, кто именно обработает.

```java
abstract class Handler {
    protected Handler next;
    public Handler setNext(Handler next) { this.next = next; return next; }  // сцепка
    public abstract String handle(int amount);
    protected String passToNext(int amount) {                  // передать дальше
        return next != null ? next.handle(amount) : "никто не обработал";
    }
}

class TeamLead extends Handler {
    public String handle(int amount) {
        if (amount <= 1000) return "TeamLead одобрил " + amount;
        return passToNext(amount);                              // не его уровень → дальше
    }
}
class Director extends Handler {
    public String handle(int amount) {
        if (amount <= 100000) return "Director одобрил " + amount;
        return passToNext(amount);
    }
}
```

```
   Запрос --▶ TeamLead --(не его)--▶ Director --(не его)--▶ ... --▶ конец
                 |обрабатывает              |обрабатывает
                 ▼                          ▼
```

Каждый обработчик решает: справиться сам или делегировать дальше. Цепочку легко перестраивать и расширять.

> Где встречается: фильтры сервлетов (`FilterChain`); middleware; обработка событий вверх по иерархии UI; уровни логирования; пайплайны валидации.

---

## Observer vs Chain of Responsibility

| | Observer | Chain of Responsibility |
|---|----------|-------------------------|
| Кто получает событие | ВСЕ подписчики | как правило ОДИН (первый, кто справится) |
| Связь | субъект → много наблюдателей | обработчик → следующий обработчик |
| Цель | оповестить об изменении | найти подходящего обработчика |
| Порядок важен | обычно нет | да (идём по цепочке) |

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Не отписали наблюдателя | «утечка», уведомления мёртвым объектам | unsubscribe; слабые ссылки при необходимости |
| Изменение списка во время уведомления | ConcurrentModificationException | копия списка перед обходом |
| Цепочка не передаёт дальше | запрос «застревает» | каждый handler либо обработал, либо вызвал next |
| Никто в цепочке не обработал | молчаливая потеря запроса | завершающий обработчик/дефолт |
| Циклическая цепочка | бесконечный проход | следить за построением (нет петель) |
| Observer с тяжёлой логикой в update() | медленный publish, побочные эффекты | update короткий; тяжёлое — асинхронно |

---

## 🔗 Связь с другими темами

- **Command (тема 10)** — событие/запрос часто оформляют как объект.
- **Decorator (тема 07)** — цепочка обёрток структурно похожа на CoR.
- **Event-Driven, Kafka (тема 24)** — Observer в распределённом масштабе (pub/sub, топики).
- **Domain Events (тема 19)** — доменные события как реализация Observer внутри домена.

## ➡️ Что дальше

Тема 12 — **State и Template Method**: как менять поведение объекта при смене его состояния (без россыпи `if`) и как задать скелет алгоритма в базовом классе, оставив шаги подклассам.
