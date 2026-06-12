# Тема 14. GRASP — распределение обязанностей

С этой темы начинается **архитектурный уровень**. Паттерны GoF (темы 04–13) — это готовые решения; **GRASP** (General Responsibility Assignment Software Patterns) — это принципы, по которым решают главный вопрос ООП: **какому классу отдать ту или иную обязанность**. Это «грамматика» проектирования, лежащая под всеми паттернами.

```
GRASP — 9 принципов; разберём ключевые 5:
  Information Expert — обязанность тому, у кого есть данные для неё
  Creator           — кто создаёт объект
  Low Coupling      — меньше связей между классами
  High Cohesion     — класс делает одну связную работу
  Controller        — кто принимает системное событие/запрос
  (+ Polymorphism, Pure Fabrication, Indirection, Protected Variations)
```

---

## 1. Information Expert — эксперт по информации

> Обязанность отдаём тому классу, у которого есть **данные**, нужные для её выполнения.

```java
// ❌ сумму заказа считает кто-то снаружи, лазая в его данные
long total = 0;
for (OrderLine l : order.getLines()) total += l.getPrice() * l.getQty();

// ✅ заказ сам знает свои строки → он и считает (он — эксперт)
class Order {
    private final List<OrderLine> lines;
    public long total() {
        long sum = 0;
        for (OrderLine l : lines) sum += l.subtotal();   // строка — эксперт по своей сумме
        return sum;
    }
}
```

Поведение тянется к данным — это уменьшает «вытаскивание» полей наружу (геттеры ради расчётов) и борется с анемичной моделью (тема 22).

---

## 2. Creator — кто создаёт

> Класс **B** должен создавать **A**, если B содержит/агрегирует A, тесно использует его или владеет данными для его инициализации.

```java
class Order {
    private final List<OrderLine> lines = new ArrayList<>();
    public void addLine(String sku, int qty, long price) {
        lines.add(new OrderLine(sku, qty, price));   // Order агрегирует OrderLine → Order и создаёт
    }
}
```

Creator подсказывает, где разместить `new`, чтобы не плодить лишних зависимостей.

---

## 3. Low Coupling — слабая связанность

> Минимизируй зависимости между классами: чем меньше класс знает о других (и о конкретных типах), тем легче менять и тестировать.

```java
// ❌ сервис привязан к конкретной реализации
class ReportService { private final MySqlOrderDao dao = new MySqlOrderDao(); }

// ✅ зависимость на абстракцию, конкретику дают снаружи (см. DIP, тема 03)
class ReportService {
    private final OrderRepository repo;
    ReportService(OrderRepository repo) { this.repo = repo; }
}
```

Низкая связанность = мало «нитей» между классами; правка одного не тянет за собой каскад изменений.

---

## 4. High Cohesion — высокая связность

> Класс должен делать **одну связную работу**, а не набор разнородных. (Это SRP под другим углом.)

```java
// ❌ низкая связность: класс и считает, и шлёт письма, и пишет в файл
class OrderManager { void calc(){} void sendEmail(){} void saveFile(){} }

// ✅ высокая связность: каждая обязанность в своём классе
class OrderCalculator { ... }
class EmailSender { ... }
class OrderRepository { ... }
```

High Cohesion и Low Coupling работают в паре: связная внутри, слабо связанная снаружи система — гибкая и понятная.

---

## 5. Controller — контроллер

> Системное событие (запрос от UI/API) принимает **контроллер** — класс, который координирует сценарий, но **не реализует бизнес-логику сам**. Он делегирует доменным объектам/сервисам.

```java
class OrderController {                  // принимает запрос, координирует
    private final OrderService service;
    OrderController(OrderService service) { this.service = service; }
    public String placeOrder(String sku, int qty) {
        return service.place(sku, qty);  // логика — в сервисе/домене, не здесь
    }
}
```

Контроллер — тонкая прослойка между «снаружи» (UI/API) и доменом. Раздувшийся контроллер с логикой — запах (он должен лишь координировать).

---

## 6. Pure Fabrication — чистая выдумка

> Если обязанность не ложится естественно ни на один доменный класс (и пихать её туда повредит High Cohesion / Low Coupling), создай **искусственный** класс-помощник.

Например, `OrderRepository`, `OrderMapper`, `PriceCalculator` — этих понятий нет в предметной области «заказов», но они держат техническую обязанность (хранение, преобразование), не загрязняя доменный `Order`. Репозитории, мапперы, сервисы — типичные Pure Fabrication.

```
   Order (доменный объект)        OrderRepository (Pure Fabrication)
   + total() — его данные         + save()/findById() — не «дело» заказа,
   + addLine() — его состав          но кому-то надо хранить → отдельный класс
```

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Логика вне эксперта | геттеры ради внешних расчётов; анемичная модель | поведение туда, где данные (Information Expert) |
| God Controller | контроллер содержит бизнес-логику | контроллер только координирует, логика — в сервисе/домене |
| Класс «обо всём» | низкая связность, много причин меняться | разнести по High Cohesion (= SRP) |
| Жёсткие `new ConcreteClass()` в логике | высокая связанность, не тестируется | зависимость на абстракцию (Low Coupling/DIP) |
| Всё в доменный объект | `Order` хранит, мапит, шлёт письма | техническое — в Pure Fabrication |

---

## 🔗 Связь с другими темами

- **SOLID (темы 02–03)** — High Cohesion ≈ SRP, Low Coupling ≈ DIP; GRASP — их «почему».
- **Controller** — основа слоя Presentation (тема 15) и адаптеров гексагона (тема 17).
- **Pure Fabrication** — репозитории/сервисы DDD (тема 19).
- **Polymorphism (GRASP)** — то же, что движет Strategy/State (темы 10, 12).

## ➡️ Что дальше

Тема 15 — **Layered, MVC, Clean Architecture**: как сгруппировать классы в слои (Presentation / Business / Persistence), что такое Model-View-Controller и как «правило зависимостей» Clean Architecture направляет связи внутрь, к домену.
