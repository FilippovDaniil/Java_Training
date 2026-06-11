# Паттерны и архитектурные стили в Java

Второй раздел репозитория — рядом с [`../java-course`](../java-course/README.md). Если основной курс ведёт **от синтаксиса до production-микросервиса**, то этот раздел развивает **архитектурное мышление**: принципы проектирования → паттерны GoF → архитектурные стили (DDD, Hexagonal, CQRS, Saga, микросервисы) → рефакторинг legacy.

Построен строго по [`../LEARNING-METHODOLOGY.md`](../LEARNING-METHODOLOGY.md): **маленькая тема → короткая теория → задачи без готовых решений → проверяемый результат → следующая тема.**

- **26 атомарных тем**, сквозная нумерация `01 → 26` (базовый → продвинутый → архитектурный уровень).
- На каждую тему — `theory.md` и **5–7 задач** (`Task01 → Task07`), седьмая — мини-проект.
- **Готовых решений нет:** условие в JavaDoc-шапке + каркас с `// TODO`. Код пишет ученик.
- **Два сквозных проекта** проходят через весь раздел (см. ниже).

> Статус генерации тем (что уже готово, что нет) — в [`PROGRESS.md`](PROGRESS.md). Ссылки на `theory.md` ниже резолвятся по мере наполнения батчами.

---

## 📁 Структура раздела

```
patterns-architecture/
├── README.md                 # этот файл — навигация по разделу
├── PROGRESS.md               # статус генерации тем по батчам
└── module-NN-topic-name/      # одна папка на тему
    ├── theory.md              # объяснение + схемы + «подводные камни» + «что дальше»
    └── practice/              # задачи для самостоятельного решения
        ├── Task01.java        # простая задача из одного класса
        ├── ...
        └── Task07/            # задача со множеством типов → отдельная папка
            ├── Task07.java    #   точка входа (main/тест) + условие в JavaDoc
            ├── Service.java   #   вспомогательные типы — каждый своим файлом
            └── ...
```

### Конвенции (унаследованы от `java-course`)

- Задача в один класс — файл `TaskNN.java`. Несколько top-level типов — папка `TaskNN/`, точка входа `TaskNN.java`, остальные типы — отдельными файлами.
- **Пакет не объявляется** (default package). Файлы внутри папки компилируются вместе.
- Условие задачи — в **JavaDoc-шапке** точки входа (это и есть «шапка задачи» из методики, §3).

```
# задача-папка (все файлы вместе):
javac -encoding UTF-8 -d out module-NN-.../practice/TaskNN/*.java
java -cp out TaskNN

# задача из одного файла:
javac -encoding UTF-8 TaskNN.java
java TaskNN
```

---

## 🧵 Сквозные проекты

Изолированные темы собираются в две реальные системы (методика, §5). Каждая тема добавляет к ним кусочек — мини-проект задачи 07 относится к одному из них.

| Проект | Домен | Роль | Финал |
|--------|-------|------|-------|
| **OPS** — Order Processing System | заказы, товары, доставка, оплата | основная линия эволюции | от консольного заказа до событийной микросервисной архитектуры |
| **BAM** — Banking Account Manager | счета, транзакции, аудит, бизнес-правила | более сложный домен, альтернативные решения | от агрегатов и доменных событий до набора микросервисов с saga |

В теме 26 (capstone) OPS и BAM сводятся: один из проектов в «запущенном» состоянии рефакторится до гексагональной архитектуры с DDD и тестами.

---

## 🗺️ Карта тем

Колонка «Мини-проект (07)» показывает, к какому сквозному проекту привязана седьмая задача темы.

### Часть 1 — Основы проектирования (01–03)

| #  | Тема | Содержание | Мини-проект (07) | Каталог |
|----|------|-----------|------------------|---------|
| 01 | Принципы ООП | инкапсуляция, наследование, полиморфизм, абстракция; композиция vs наследование | OPS | [`m01_oop_principles`](m01_oop_principles/theory.md) |
| 02 | SOLID: SRP, OCP | одна ответственность; открыт для расширения, закрыт для изменения | OPS | [`m02_solid_srp_ocp`](m02_solid_srp_ocp/theory.md) |
| 03 | SOLID: LSP, ISP, DIP | подстановка Лисков, разделение интерфейсов, инверсия зависимостей | BAM | [`m03_solid_lsp_isp_dip`](m03_solid_lsp_isp_dip/theory.md) |

### Часть 2 — Порождающие паттерны (04–06)

| #  | Тема | Содержание | Мини-проект (07) | Каталог |
|----|------|-----------|------------------|---------|
| 04 | Singleton, Factory Method | потокобезопасный Singleton, enum; фабричный метод | OPS | [`m04_singleton_factory_method`](m04_singleton_factory_method/theory.md) |
| 05 | Abstract Factory, Builder | семейства объектов; пошаговое построение, fluent | BAM | [`m05_abstract_factory_builder`](m05_abstract_factory_builder/theory.md) |
| 06 | Prototype, Object Pool | клонирование (clone / copy ctor); пул тяжёлых объектов | OPS | [`m06_prototype_pool`](m06_prototype_pool/theory.md) |

### Часть 3 — Структурные паттерны (07–09)

| #  | Тема | Содержание | Мини-проект (07) | Каталог |
|----|------|-----------|------------------|---------|
| 07 | Adapter, Decorator | конвертация интерфейса; динамическое добавление поведения | BAM | [`m07_adapter_decorator`](m07_adapter_decorator/theory.md) |
| 08 | Proxy, Facade | заместитель (защита/ленивость/лог); упрощённый интерфейс | OPS | [`m08_proxy_facade`](m08_proxy_facade/theory.md) |
| 09 | Composite, Bridge | древовидные структуры; разделение абстракции и реализации | BAM | [`m09_composite_bridge`](m09_composite_bridge/theory.md) |

### Часть 4 — Поведенческие паттерны (10–13)

| #  | Тема | Содержание | Мини-проект (07) | Каталог |
|----|------|-----------|------------------|---------|
| 10 | Strategy, Command | семейство алгоритмов; запрос как объект, undo | OPS | [`m10_strategy_command`](m10_strategy_command/theory.md) |
| 11 | Observer, Chain of Responsibility | подписка на события; цепочка обработчиков | BAM | [`m11_observer_chain_of_responsibility`](m11_observer_chain_of_responsibility/theory.md) |
| 12 | State, Template Method | поведение от состояния; скелет алгоритма с хуками | OPS | [`m12_state_template_method`](m12_state_template_method/theory.md) |
| 13 | Iterator, Visitor | обход агрегата; операции над иерархией, double dispatch | BAM | [`m13_iterator_visitor`](m13_iterator_visitor/theory.md) |

### Часть 5 — Архитектурные принципы и паттерны (14–21)

| #  | Тема | Содержание | Мини-проект (07) | Каталог |
|----|------|-----------|------------------|---------|
| 14 | GRASP | Information Expert, Low Coupling, High Cohesion, Controller, Pure Fabrication | OPS | [`m14_grasp`](m14_grasp/theory.md) |
| 15 | Layered (MVC, Clean) | слои Presentation/Business/Persistence; MVC; Clean Architecture | BAM | [`m15_layered_mvc_clean`](m15_layered_mvc_clean/theory.md) |
| 16 | DI и IoC | DI как реализация DIP; контейнеры; ручное внедрение; Service Locator | OPS | [`m16_dependency_injection_ioc`](m16_dependency_injection_ioc/theory.md) |
| 17 | Hexagonal (Ports & Adapters) | ядро + порты + адаптеры; независимость от БД/Web; тестируемость | BAM | [`m17_hexagonal_ports_adapters`](m17_hexagonal_ports_adapters/theory.md) |
| 18 | Strategic DDD | Bounded Context, Ubiquitous Language, Entity, Value Object, Aggregate | OPS | [`m18_ddd_strategic`](m18_ddd_strategic/theory.md) |
| 19 | Tactical DDD | Repositories, доменные сервисы, фабрики, Domain Events | BAM | [`m19_ddd_tactical`](m19_ddd_tactical/theory.md) |
| 20 | Event Sourcing, CQRS | состояние как поток событий; разделение команд и запросов, проекции | OPS | [`m20_event_sourcing_cqrs`](m20_event_sourcing_cqrs/theory.md) |
| 21 | Saga (распределённые транзакции) | хореография vs оркестрация; компенсирующие действия | BAM | [`m21_saga_distributed_transactions`](m21_saga_distributed_transactions/theory.md) |

### Часть 6 — Продвинутые архитектурные принципы (22–26)

| #  | Тема | Содержание | Мини-проект (07) | Каталог |
|----|------|-----------|------------------|---------|
| 22 | Антипаттерны | Anemic Domain Model, God Object, Lava Flow, Golden Hammer; рефакторинг | OPS | [`m22_antipatterns`](m22_antipatterns/theory.md) |
| 23 | Microservices vs Monolith | стили, trade-offs, границы сервисов, REST/gRPC/messaging, Modular Monolith | BAM | [`m23_microservices_vs_monolith`](m23_microservices_vs_monolith/theory.md) |
| 24 | Асинхронные архитектуры | Message Queue, Pub-Sub, Event-Driven, Kafka, гарантии доставки, DLQ | OPS | [`m24_messaging_event_driven_kafka`](m24_messaging_event_driven_kafka/theory.md) |
| 25 | Тестирование архитектуры | ArchUnit (слои, циклы), Testcontainers, снимок архитектуры в CI | BAM | [`m25_architecture_testing_archunit`](m25_architecture_testing_archunit/theory.md) |
| 26 | Рефакторинг legacy (capstone) | strangle pattern, feature toggles, parallel run; сведение OPS + BAM | OPS + BAM | [`m26_legacy_refactoring_capstone`](m26_legacy_refactoring_capstone/theory.md) |

---

## 🧩 Анатомия задачи (методика, §3)

В каждой задаче — только условие и каркас. Шапка в JavaDoc точки входа:

```java
/**
 * Задача NN — Модуль NN: <название>
 *
 * ЗАДАНИЕ:
 *   что нужно сделать (1–3 предложения).
 *
 * ПРИМЕР / ВЫВОД:
 *   как выглядит правильный результат.
 *
 * ТРЕБОВАНИЯ:
 *   ограничения и обязательные условия (например: «domain не импортирует Spring»).
 *
 * ПОДСКАЗКА:
 *   направление, но НЕ ответ.
 */
public class TaskNN {
    public static void main(String[] args) {
        // TODO: ваш код
    }
}
```

**Правило:** готового решения нет. Есть только то, что помогает начать.

### Две оси нарастающей сложности (методика, §4)

```
Внутри темы (01 → 07):
  01–03  базовое применение паттерна/принципа
  04–06  комбинации, нестандартные случаи, сравнения
  07     мини-проект — синтез темы в OPS или BAM

Между темами (01 → 26):
  Часть 1 (01–03)   азы ООП и SOLID
  Часть 2–4 (04–13) классические паттерны GoF
  Часть 5–6 (14–26) архитектурные принципы и стили
```

---

## ✅ Объективная проверяемость (методика, §10, §12)

Формат проверки подстраивается под тип темы:

| Тип темы | Темы | Как проверять |
|----------|------|---------------|
| Консольные приложения | большинство | компиляция + запуск + ожидаемый вывод в шапке |
| Библиотеки/API | паттерны | пример использования + `assert` / JUnit (тесты проверяют интерфейс, не содержат решения) |
| Архитектурные правила | 17, 22, 25, 26 | чек-лист («пакет `domain` не импортирует Spring») + ArchUnit-тест |
| С зависимостями (Spring, Kafka, Testcontainers) | 16, 20, 21, 23–25 | в шапке — «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; запуск в IDE/Gradle |

---

## 🔄 Прогресс и батчи

Материал готовится **батчами с проверкой после каждого** (методика, §6); статус — на диске в [`PROGRESS.md`](PROGRESS.md), а не «в контексте». Работу можно прервать и продолжить с того же места.

---

## ⚙️ Окружение

| Что | Требование |
|-----|------------|
| JDK | 17 LTS |
| IDE | IntelliJ IDEA (Community достаточно) |
| Сборка | Core-задачи — напрямую `javac`; темы с зависимостями — Gradle |
| Доп. | темы 23–25 предполагают Docker (Testcontainers, брокеры) |

---

## 🚀 Рекомендации по изучению

1. **Сначала** пройдите [`../java-course`](../java-course/README.md) хотя бы до модуля 30 (ООП, коллекции, паттерны-обзор) — этот раздел опирается на него.
2. Идите темами **сверху вниз**: 14–26 требуют понимания 01–13.
3. На каждой теме: `theory.md` → задачи `Task01 → Task07`.
4. Ведите оба сквозных проекта (OPS, BAM) — к теме 26 они достигают промышленного уровня.
5. **Готовых решений нет** — пишете вы.
