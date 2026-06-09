# PROGRESS — Паттерны и архитектурные стили

Статус генерации раздела `patterns-architecture/`. Хранится на диске (методика, §6): работу можно прервать и продолжить с того же места.

**Легенда:** ⬜ не начато · 🟡 в работе · ✅ готово (theory.md + все задачи) · 🔍 прошло проверку батча

**На текущий момент:** Батч 1 (темы 01–03) готов и прошёл проверку компиляцией. Темы 04–26 ещё не генерировались.

---

## Батчи (по 3–5 тем, проверка после каждого)

После каждого батча — объективная проверка: компиляция/запуск задач, прогон JUnit/ArchUnit-тестов, затем фиксация статуса здесь и git-commit.

| Батч | Темы | Часть | Статус |
|------|------|-------|--------|
| 1 | 01–03 | Основы проектирования (ООП, SOLID) | 🔍 |
| 2 | 04–06 | Порождающие паттерны | ⬜ |
| 3 | 07–09 | Структурные паттерны | ⬜ |
| 4 | 10–13 | Поведенческие паттерны | ⬜ |
| 5 | 14–17 | GRASP, Layered, DI/IoC, Hexagonal | ⬜ |
| 6 | 18–21 | DDD (strategic/tactical), ES/CQRS, Saga | ⬜ |
| 7 | 22–26 | Антипаттерны → стили → capstone | ⬜ |

> Перед каждым батчем — подтверждение пользователя (контроль токенов).

---

## Темы

| #  | Тема | Каталог | theory.md | Задачи | Проверка |
|----|------|---------|-----------|--------|----------|
| 01 | Принципы ООП | `module-01-oop-principles` | ✅ | ✅ 7/7 | 🔍 |
| 02 | SOLID: SRP, OCP | `module-02-solid-srp-ocp` | ✅ | ✅ 7/7 | 🔍 |
| 03 | SOLID: LSP, ISP, DIP | `module-03-solid-lsp-isp-dip` | ✅ | ✅ 7/7 | 🔍 |
| 04 | Singleton, Factory Method | `module-04-singleton-factory-method` | ⬜ | ⬜ 0/7 | ⬜ |
| 05 | Abstract Factory, Builder | `module-05-abstract-factory-builder` | ⬜ | ⬜ 0/7 | ⬜ |
| 06 | Prototype, Object Pool | `module-06-prototype-pool` | ⬜ | ⬜ 0/7 | ⬜ |
| 07 | Adapter, Decorator | `module-07-adapter-decorator` | ⬜ | ⬜ 0/7 | ⬜ |
| 08 | Proxy, Facade | `module-08-proxy-facade` | ⬜ | ⬜ 0/7 | ⬜ |
| 09 | Composite, Bridge | `module-09-composite-bridge` | ⬜ | ⬜ 0/7 | ⬜ |
| 10 | Strategy, Command | `module-10-strategy-command` | ⬜ | ⬜ 0/7 | ⬜ |
| 11 | Observer, Chain of Responsibility | `module-11-observer-chain-of-responsibility` | ⬜ | ⬜ 0/7 | ⬜ |
| 12 | State, Template Method | `module-12-state-template-method` | ⬜ | ⬜ 0/7 | ⬜ |
| 13 | Iterator, Visitor | `module-13-iterator-visitor` | ⬜ | ⬜ 0/7 | ⬜ |
| 14 | GRASP | `module-14-grasp` | ⬜ | ⬜ 0/7 | ⬜ |
| 15 | Layered (MVC, Clean) | `module-15-layered-mvc-clean` | ⬜ | ⬜ 0/7 | ⬜ |
| 16 | DI и IoC | `module-16-dependency-injection-ioc` | ⬜ | ⬜ 0/7 | ⬜ |
| 17 | Hexagonal (Ports & Adapters) | `module-17-hexagonal-ports-adapters` | ⬜ | ⬜ 0/7 | ⬜ |
| 18 | Strategic DDD | `module-18-ddd-strategic` | ⬜ | ⬜ 0/7 | ⬜ |
| 19 | Tactical DDD | `module-19-ddd-tactical` | ⬜ | ⬜ 0/7 | ⬜ |
| 20 | Event Sourcing, CQRS | `module-20-event-sourcing-cqrs` | ⬜ | ⬜ 0/7 | ⬜ |
| 21 | Saga | `module-21-saga-distributed-transactions` | ⬜ | ⬜ 0/7 | ⬜ |
| 22 | Антипаттерны | `module-22-antipatterns` | ⬜ | ⬜ 0/7 | ⬜ |
| 23 | Microservices vs Monolith | `module-23-microservices-vs-monolith` | ⬜ | ⬜ 0/7 | ⬜ |
| 24 | Асинхронные архитектуры (Kafka) | `module-24-messaging-event-driven-kafka` | ⬜ | ⬜ 0/7 | ⬜ |
| 25 | Тестирование архитектуры (ArchUnit) | `module-25-architecture-testing-archunit` | ⬜ | ⬜ 0/7 | ⬜ |
| 26 | Рефакторинг legacy (capstone) | `module-26-legacy-refactoring-capstone` | ⬜ | ⬜ 0/7 | ⬜ |

---

## Журнал

- **2026-06-09 — Батч 1 (темы 01–03):** ООП-принципы, SOLID SRP/OCP, SOLID LSP/ISP/DIP. По каждой теме — `theory.md` + 7 задач (каркасы без решений). Запущены сквозные проекты: OPS (тема 01/07 — каталог товаров; тема 02/07 — расчёт заказа) и BAM (тема 03/07 — перевод между счетами). Проверка: все 21 задача компилируются (`javac -encoding UTF-8`, 98 .java-файлов, 0 ошибок).
