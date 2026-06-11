# PROGRESS — Паттерны и архитектурные стили

Статус генерации раздела `patterns-architecture/`. Хранится на диске (методика, §6): работу можно прервать и продолжить с того же места.

**Легенда:** ⬜ не начато · 🟡 в работе · ✅ готово (theory.md + все задачи) · 🔍 прошло проверку батча

**На текущий момент:** 🎉 **РАЗДЕЛ ЗАВЕРШЁН** — все 26 тем готовы и прошли проверку компиляцией (182 задачи, 843 .java, 0 ошибок). Весь путь: принципы ООП/SOLID → паттерны GoF → архитектурные стили (GRASP, Layered, DI/IoC, Hexagonal) → DDD/ES-CQRS/Saga → антипаттерны/микросервисы/messaging/арх-тесты → capstone (рефакторинг legacy, OPS+BAM). Дальше — только расширение по запросу пользователя.

---

## Батчи (по 3–5 тем, проверка после каждого)

После каждого батча — объективная проверка: компиляция/запуск задач, прогон JUnit/ArchUnit-тестов, затем фиксация статуса здесь и git-commit.

| Батч | Темы | Часть | Статус |
|------|------|-------|--------|
| 1 | 01–03 | Основы проектирования (ООП, SOLID) | 🔍 |
| 2 | 04–06 | Порождающие паттерны | 🔍 |
| 3 | 07–09 | Структурные паттерны | 🔍 |
| 4 | 10–13 | Поведенческие паттерны | 🔍 |
| 5 | 14–17 | GRASP, Layered, DI/IoC, Hexagonal | 🔍 |
| 6 | 18–21 | DDD (strategic/tactical), ES/CQRS, Saga | 🔍 |
| 7 | 22–26 | Антипаттерны → стили → capstone | 🔍 |

> Перед каждым батчем — подтверждение пользователя (контроль токенов).

---

## Темы

| #  | Тема | Каталог | theory.md | Задачи | Проверка |
|----|------|---------|-----------|--------|----------|
| 01 | Принципы ООП | `m01_oop_principles` | ✅ | ✅ 7/7 | 🔍 |
| 02 | SOLID: SRP, OCP | `m02_solid_srp_ocp` | ✅ | ✅ 7/7 | 🔍 |
| 03 | SOLID: LSP, ISP, DIP | `m03_solid_lsp_isp_dip` | ✅ | ✅ 7/7 | 🔍 |
| 04 | Singleton, Factory Method | `m04_singleton_factory_method` | ✅ | ✅ 7/7 | 🔍 |
| 05 | Abstract Factory, Builder | `m05_abstract_factory_builder` | ✅ | ✅ 7/7 | 🔍 |
| 06 | Prototype, Object Pool | `m06_prototype_pool` | ✅ | ✅ 7/7 | 🔍 |
| 07 | Adapter, Decorator | `m07_adapter_decorator` | ✅ | ✅ 7/7 | 🔍 |
| 08 | Proxy, Facade | `m08_proxy_facade` | ✅ | ✅ 7/7 | 🔍 |
| 09 | Composite, Bridge | `m09_composite_bridge` | ✅ | ✅ 7/7 | 🔍 |
| 10 | Strategy, Command | `m10_strategy_command` | ✅ | ✅ 7/7 | 🔍 |
| 11 | Observer, Chain of Responsibility | `m11_observer_chain_of_responsibility` | ✅ | ✅ 7/7 | 🔍 |
| 12 | State, Template Method | `m12_state_template_method` | ✅ | ✅ 7/7 | 🔍 |
| 13 | Iterator, Visitor | `m13_iterator_visitor` | ✅ | ✅ 7/7 | 🔍 |
| 14 | GRASP | `m14_grasp` | ✅ | ✅ 7/7 | 🔍 |
| 15 | Layered (MVC, Clean) | `m15_layered_mvc_clean` | ✅ | ✅ 7/7 | 🔍 |
| 16 | DI и IoC | `m16_dependency_injection_ioc` | ✅ | ✅ 7/7 | 🔍 |
| 17 | Hexagonal (Ports & Adapters) | `m17_hexagonal_ports_adapters` | ✅ | ✅ 7/7 | 🔍 |
| 18 | Strategic DDD | `m18_ddd_strategic` | ✅ | ✅ 7/7 | 🔍 |
| 19 | Tactical DDD | `m19_ddd_tactical` | ✅ | ✅ 7/7 | 🔍 |
| 20 | Event Sourcing, CQRS | `m20_event_sourcing_cqrs` | ✅ | ✅ 7/7 | 🔍 |
| 21 | Saga | `m21_saga_distributed_transactions` | ✅ | ✅ 7/7 | 🔍 |
| 22 | Антипаттерны | `m22_antipatterns` | ✅ | ✅ 7/7 | 🔍 |
| 23 | Microservices vs Monolith | `m23_microservices_vs_monolith` | ✅ | ✅ 7/7 | 🔍 |
| 24 | Асинхронные архитектуры (Kafka) | `m24_messaging_event_driven_kafka` | ✅ | ✅ 7/7 | 🔍 |
| 25 | Тестирование архитектуры (ArchUnit) | `m25_architecture_testing_archunit` | ✅ | ✅ 7/7 | 🔍 |
| 26 | Рефакторинг legacy (capstone) | `m26_legacy_refactoring_capstone` | ✅ | ✅ 7/7 | 🔍 |

---

## Журнал

- **2026-06-09 — Батч 1 (темы 01–03):** ООП-принципы, SOLID SRP/OCP, SOLID LSP/ISP/DIP. По каждой теме — `theory.md` + 7 задач (каркасы без решений). Запущены сквозные проекты: OPS (тема 01/07 — каталог товаров; тема 02/07 — расчёт заказа) и BAM (тема 03/07 — перевод между счетами). Проверка: все 21 задача компилируются (`javac -encoding UTF-8`, 98 .java-файлов, 0 ошибок).
- **2026-06-09 — Батч 2 (темы 04–06):** порождающие паттерны — Singleton/Factory Method, Abstract Factory/Builder, Prototype/Object Pool. По каждой теме `theory.md` + 7 задач-каркасов. Развитие сквозных проектов: OPS (04/07 — нумерация+фабрика заказов; 06/07 — шаблоны заказов + пул шлюзов), BAM (05/07 — продуктовые семьи Retail/Corporate + Builder выписки). Проверка: все 21 задача компилируются (97 .java, 0 ошибок).
- **2026-06-09 — Батч 3 (темы 07–09):** структурные паттерны — Adapter/Decorator, Proxy/Facade, Composite/Bridge. По каждой теме `theory.md` + 7 задач-каркасов. Развитие сквозных проектов: BAM (07/07 — импорт транзакций Adapter + аудит Decorator; 09/07 — портфель счетов Composite + формат выписки Bridge), OPS (08/07 — Facade оформления + кеширующий Proxy каталога). Проверка: все 21 задача компилируются (113 .java, 0 ошибок).
- **2026-06-09 — Батч 4 (темы 10–13):** поведенческие паттерны — Strategy/Command, Observer/CoR, State/Template Method, Iterator/Visitor. По каждой теме `theory.md` + 7 задач-каркасов (28 задач; m10/Task02 — single-file демонстрация Strategy через лямбду). Развитие: OPS (10/07 — редактирование заказа командами с undo + стратегия скидки; 12/07 — State жизненного цикла заказа + Template Method пайплайна), BAM (11/07 — CoR валидации транзакций + Observer уведомлений; 13/07 — Iterator истории транзакций + Visitor отчёта). Проверка: все 28 задач компилируются (157 .java, 0 ошибок).
- **2026-06-09 — Батч 5, часть 1 (темы 14–15):** архитектурные принципы — GRASP (Information Expert, Creator, Low Coupling, High Cohesion, Controller, Pure Fabrication) и Layered/MVC/Clean. По каждой теме `theory.md` + 7 задач-каркасов (14 задач). Развитие: OPS (14/07 — оформление заказа по GRASP: Controller→Service→Repository + Information Expert + Pure Fabrication), BAM (15/07 — слоистый банковский модуль Presentation→Business→Persistence + DTO). Темы чистого Java (проверка `javac`). Проверка: все 14 задач компилируются (63 .java, 0 ошибок).
- **2026-06-09 — Батч 5, часть 2 (темы 16–17):** DI/IoC и Hexagonal — ЗАВЕРШАЕТ Батч 5. По каждой теме `theory.md` + 7 задач-каркасов (14 задач). Тема 16: ручной DI (constructor/setter injection), свой IoC-контейнер (Map рецептов), Service Locator (антипаттерн), тестируемость через двойники. Тема 17: входные/выходные порты, адаптеры, полный гексагон, замена адаптеров, тестирование ядра fake-адаптером. Решено держать чистым Java (Spring упомянут в теории как «настоящий» контейнер) — весь раздел остаётся проверяемым `javac`. Развитие: OPS (16/07 — сборка Controller→Service→Repository контейнером), BAM (17/07 — перевод в гексагоне: driving-адаптер→TransferUseCase→ядро→AccountRepository→driven-адаптер). Проверка: все 14 задач компилируются (75 .java, 0 ошибок).
- **2026-06-09 — Батч 7 (темы 22–26) — РАЗДЕЛ ЗАВЕРШЁН:** антипаттерны (Anemic/God Object/Primitive Obsession/Feature Envy/Long Parameter List + рефактор), Microservices vs Monolith (модули/контракты/границы/sync-vs-async/модульный монолит), Messaging/Event-Driven (очередь/pub-sub/идемпотентность/DLQ/retry), Architecture testing (hand-rolled проверки слоёв/циклов/именования/доступа/гексагона вместо ArchUnit), Legacy capstone (Strangler/Toggle/Parallel Run/Branch by Abstraction/ACL/seam + финальный рефактор god→гексагон, OPS+BAM). По каждой теме theory.md + 7 задач (35 задач). Чистый Java. Проверка: все 35 задач компилируются (124 .java, 0 ошибок). **ИТОГ РАЗДЕЛА: 26/26 тем, 182 задачи, 843 .java, всё компилируется `javac -encoding UTF-8`.**
- **2026-06-09 — Батч 6 (темы 18–21):** DDD и распределённые транзакции. Тема 18 (Strategic DDD: Value Object, Entity, Aggregate, Bounded Context/Ubiquitous Language), 19 (Tactical DDD: Repository, Domain Service, Factory, Domain Events + dispatcher), 20 (Event Sourcing: replay/append; CQRS: read/write модели, проекции, command handler + event store), 21 (Saga: шаги+компенсации, оркестрация с откатом, хореография через EventBus, boolean-откат, идемпотентность). По каждой теме `theory.md` + 7 задач-каркасов (28 задач; события — Java records). Развитие: OPS (18/07 агрегат Order + Money VO; 20/07 заказ как поток событий + проекция), BAM (19/07 Account-агрегат + Repository + Domain Service + события; 21/07 saga перевода debit→credit + компенсация). Чистый Java. Проверка: все 28 задач компилируются (116 .java, 0 ошибок).
