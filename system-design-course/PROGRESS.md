# PROGRESS — System Design (middle+ → senior)

Статус генерации раздела `system-design-course/` и **план генерации** (источник истины). Работу можно прервать и продолжить с того же места: при потере контекста читай этот файл и продолжай со следующего невыполненного батча.

**Легенда:** ⬜ не начато · 🟡 в работе · ✅ готово (theory.md + 10 задач + 25 вопросов) · 🔍 прошло проверку батча

**Формат модуля:** `module-NN-topic/` → `theory.md` + `practice/Task01..Task10.md` + `questions.md` (25 вопросов **в стиле design-интервью middle+/senior** + ключ эталонных ответов в конце файла). Готовых решений нет: условие + каркас артефакта + критерии приёмки. Десятая задача каждого модуля — мини-проект (синтез темы).

**Тип артефакта по задаче** (вместо «компилируется/нет» — критерии приёмки):
расчёт (estimation/capacity/cost) · диаграмма C4/компонентов/sequence · дизайн-документ (HLD) · сравнительная таблица trade-off · ADR · API-контракт / JSON Schema.

**Сквозные примеры:** блоки (Части 3–7) иллюстрируются мини-кейсами реальных систем; Часть 8 — 14 полных референсных архитектур; `C` = capstone (новый домен с нуля, модуль 48).

**Режим генерации:** 3 модуля за батч (как в `system-analysis-course`), пауза/подтверждение пользователя перед каждым батчем (контроль токенов). Часть 8 (14 модулей) разбита на под-батчи.

**На текущий момент:** 🟡 **В работе — готовы Батчи 1–9 (Части 1–6 полностью, модули 01–28): 28/49.** Сгенерировано 336 .md (28×[theory + 10 задач + questions]), скан на кракозябры/NUL/CJK — чисто. **Части 1–6 ЗАВЕРШЕНЫ (Часть 6 — 5/5: масштаб/отказоустойчивость, multi-region/DR, узкие места, нагрузка/chaos, observability).** Дальше Батч 10 (модули 29–31: AuthN/AuthZ, защита API, развёртывание — Часть 7 «Безопасность и эксплуатация»). Детальный план каждого модуля (что обязательно в `theory.md` + темы 10 задач) дописывается в этот файл в начале соответствующего батча, перед генерацией.

---

## Батчи (подтверждение пользователя перед каждым — контроль токенов)

После каждого батча: проверка артефактов (валидность нотаций C4/sequence, OpenAPI/JSON Schema, прозрачность расчётов по критериям) → скан на кракозябры/NUL/CJK → фиксация статуса здесь → git-commit.

| Батч | Модули | Часть | Статус |
|------|--------|-------|--------|
| 0 | — | Скелет (README + PROGRESS) | ✅ |
| 1 | 01–03 | Ч.1 Основы дизайна | 🔍 |
| 2 | 04–07 | Ч.2 Количественное проектирование | 🔍 |
| 3 | 08–10 | Ч.3 Строительные блоки (a) | 🔍 |
| 4 | 11–13 | Ч.3 Строительные блоки (b) | 🔍 |
| 5 | 14–17 | Ч.3 Строительные блоки (c) | 🔍 |
| 6 | 18–20 | Ч.4 Данные и аналитика на масштабе | 🔍 |
| 7 | 21–23 | Ч.5 Распределённые системы | 🔍 |
| 8 | 24–26 | Ч.6 Надёжность/производительность (a) | 🔍 |
| 9 | 27–28 | Ч.6 Надёжность/производительность (b) | 🔍 |
| 10 | 29–31 | Ч.7 Безопасность и эксплуатация | ⬜ |
| 11 | 32–34 | Ч.8 Референсные архитектуры (a) | ⬜ |
| 12 | 35–37 | Ч.8 Референсные архитектуры (b) | ⬜ |
| 13 | 38–40 | Ч.8 Референсные архитектуры (c) | ⬜ |
| 14 | 41–43 | Ч.8 Референсные архитектуры (d) | ⬜ |
| 15 | 44–45 | Ч.8 Референсные архитектуры (e) | ⬜ |
| 16 | 46–47 | Ч.9 Подготовка к интервью | ⬜ |
| 17 | 48–49 | Ч.10 Capstone + ИИ | ⬜ |

> Батчи выровнены по границам частей (внутри батча часть не «рвётся»). Часть 8 (14 систем) — 5 под-батчей. Итого 17 батчей контента + скелет.

---

## Статус модулей

### Часть 1 — Основы дизайна
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 01 | Что такое System Design | `module-01-what-is-system-design` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 02 | Документирование дизайна (ADR, C4, arc42, Doc-as-Code) | `module-02-documenting-design` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 03 | Фреймворк design-интервью | `module-03-interview-framework` | ✅ | ✅ 10/10 | ✅ | 🔍 |

### Часть 2 — Количественное проектирование
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 04 | Требования для дизайна (FR/NFR/ограничения) | `module-04-design-requirements` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 05 | Оценка нагрузки (back-of-the-envelope) | `module-05-load-estimation` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 06 | Оценка ёмкости (storage/bandwidth/memory) | `module-06-capacity-estimation` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 07 | Cost Estimation (TCO, $/запрос) | `module-07-cost-estimation` | ✅ | ✅ 10/10 | ✅ | 🔍 |

### Часть 3 — Строительные блоки на масштабе
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 08 | Балансировка нагрузки | `module-08-load-balancing` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 09 | Кэширование на масштабе | `module-09-caching` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 10 | CDN и хранение объектов/медиа | `module-10-cdn-blob-storage` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 11 | Базы данных в дизайне (SQL vs NoSQL) | `module-11-databases-in-design` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 12 | Репликация и согласованность | `module-12-replication-consistency` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 13 | Шардирование и партиционирование | `module-13-sharding-partitioning` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 14 | Consistent hashing и уникальные ID | `module-14-consistent-hashing-ids` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 15 | Очереди и стриминг сообщений | `module-15-queues-streaming` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 16 | Rate limiting & throttling | `module-16-rate-limiting` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 17 | Поиск, автодополнение и геосервисы | `module-17-search-geo` | ✅ | ✅ 10/10 | ✅ | 🔍 |

### Часть 4 — Данные и аналитика на масштабе
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 18 | OLTP vs OLAP, DWH, колоночные БД | `module-18-oltp-olap-dwh` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 19 | Data Lake, Lakehouse, Data Mesh | `module-19-lake-lakehouse-mesh` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 20 | Конвейеры данных (ETL/ELT, streaming, CDC, lineage) | `module-20-data-pipelines` | ✅ | ✅ 10/10 | ✅ | 🔍 |

### Часть 5 — Распределённые системы для дизайна
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 21 | CAP/PACELC и выбор согласованности | `module-21-cap-pacelc` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 22 | Координация и консенсус (Raft, leader election, locks) | `module-22-coordination-consensus` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 23 | Надёжность операций (идемпотентность, Saga/Outbox, resilience) | `module-23-operation-reliability` | ✅ | ✅ 10/10 | ✅ | 🔍 |

### Часть 6 — Надёжность, производительность, масштаб
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 24 | Масштабируемость и отказоустойчивость | `module-24-scalability-fault-tolerance` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 25 | Multi-region и Disaster Recovery | `module-25-multi-region-dr` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 26 | Анализ узких мест и оптимизация | `module-26-bottlenecks` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 27 | Нагрузочное тестирование и Chaos Engineering | `module-27-load-testing-chaos` | ✅ | ✅ 10/10 | ✅ | 🔍 |
| 28 | Observability для дизайна (SLI/SLO, error budget) | `module-28-observability` | ✅ | ✅ 10/10 | ✅ | 🔍 |

### Часть 7 — Безопасность и эксплуатация
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 29 | AuthN/AuthZ в дизайне (OAuth2/OIDC/JWT, SSO) | `module-29-authn-authz` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 30 | Защита API и противодействие атакам | `module-30-api-protection` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 31 | Развёртывание (контейнеры/оркестрация/CI-CD топология) | `module-31-deployment` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |

### Часть 8 — Референсные архитектуры (разбор реальных систем)
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 32 | URL-shortener / Pastebin | `module-32-url-shortener` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 33 | Distributed KV-store / распределённый кэш | `module-33-distributed-kv-store` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 34 | Newsfeed / лента соцсети | `module-34-newsfeed` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 35 | Чат / мессенджер | `module-35-messenger` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 36 | Система уведомлений | `module-36-notifications` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 37 | Видеостриминг (YouTube/Netflix) | `module-37-video-streaming` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 38 | Облачное файловое хранилище (Dropbox/Drive) | `module-38-file-storage` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 39 | Сервис такси / ride-sharing | `module-39-ride-sharing` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 40 | Платёжная система | `module-40-payment-system` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 41 | Рекламная система / агрегация кликов | `module-41-ad-system` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 42 | Веб-краулер / поисковый индекс | `module-42-web-crawler` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 43 | Leaderboard / рейтинги | `module-43-leaderboard` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 44 | Распределённый планировщик задач | `module-44-job-scheduler` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 45 | Система метрик и мониторинга | `module-45-metrics-system` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |

### Часть 9 — Подготовка к интервью
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 46 | Классические задачи и методика ответа | `module-46-interview-classics` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 47 | Симуляция интервью + рубрика по грейдам | `module-47-interview-simulation` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |

### Часть 10 — Финал
| #  | Модуль | Каталог | theory | Задачи | Вопросы | Проверка |
|----|--------|---------|--------|--------|---------|----------|
| 48 | Capstone — дизайн нового домена с нуля | `module-48-capstone` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |
| 49 | Бонус: ИИ в System Design | `module-49-ai-for-system-design` | ⬜ | ⬜ 0/10 | ⬜ | ⬜ |

---

## Сводка

| Часть | Модули | Кол-во | Статус |
|-------|--------|--------|--------|
| 1 — Основы дизайна | 01–03 | 3 | ✅ 3/3 |
| 2 — Количественное проектирование | 04–07 | 4 | ✅ 4/4 |
| 3 — Строительные блоки на масштабе | 08–17 | 10 | ✅ 10/10 |
| 4 — Данные и аналитика на масштабе | 18–20 | 3 | ✅ 3/3 |
| 5 — Распределённые системы | 21–23 | 3 | ✅ 3/3 |
| 6 — Надёжность/производительность/масштаб | 24–28 | 5 | ✅ 5/5 |
| 7 — Безопасность и эксплуатация | 29–31 | 3 | ⬜ 0/3 |
| 8 — Референсные архитектуры | 32–45 | 14 | ⬜ 0/14 |
| 9 — Подготовка к интервью | 46–47 | 2 | ⬜ 0/2 |
| 10 — Финал (capstone + ИИ) | 48–49 | 2 | ⬜ 0/2 |
| **Итого** | **01–49** | **49** | **🟡 28/49** |

**Ожидаемый объём при завершении:** 49 модулей × (1 `theory.md` + 10 задач + 1 `questions.md`) + `README.md` + `PROGRESS.md` = **590 .md**.

---

## Детальный план по батчам (источник истины; дописывается перед генерацией батча)

### Батч 1 — Часть 1 «Основы дизайна» (модули 01–03)

**Модуль 01 — Что такое System Design**
Теория: SD vs системный анализ (что vs как) vs архитектура ПО; HLD vs LLD; функциональные/нефункциональные аспекты; оси дизайна (нагрузка, данные, согласованность, надёжность, стоимость, латентность); процесс дизайна (требования → estimation → API → data → high-level → deep dive → bottlenecks → wrap-up); признаки хорошего дизайна; trade-off-мышление.
Задачи: 01 разграничить SD/СА/архитектуру (таблица) · 02 классифицировать решения HLD/LLD · 03 прочитать готовый дизайн, выделить ключевые решения · 04 перечислить оси дизайна для системы · 05 найти необоснованные решения (без trade-off) и переформулировать · 06 чек-лист «хороший дизайн» под кейс · 07 разложить процесс дизайна по шагам · 08 FR vs NFR и как NFR двигает дизайн · 09 кейс «дизайн без оценки нагрузки» — риски · 10 мини-проект: one-pager дизайна (scope/FR/NFR/оси/открытые вопросы).
Вопросы (25): определение SD, отличия от СА/архитектуры, HLD/LLD, оси, процесс, признаки качества, trade-off.

**Модуль 02 — Документирование дизайна (ADR, C4, arc42, Doc-as-Code)**
Теория: зачем документировать; C4 (Context/Container/Component/Code) — уровни и нотация; ADR (контекст→варианты→решение→последствия, статусы); arc42 (структура шаблона); Doc-as-Code (PlantUML/Structurizr/Mermaid, дока в репо, ревью как код); sequence-диаграммы; что документировать на каждом уровне; антипаттерны (диаграмма ради диаграммы, устаревшая дока).
Задачи: 01 C4 Context системы · 02 C4 Container (+ протоколы) · 03 ADR на SQL vs NoSQL · 04 разбор чужого ADR (пропущенные альтернативы/последствия) · 05 выбрать уровень C4 под аудиторию · 06 найти ошибки в C4 (смешение уровней, нет протоколов) · 07 структура дизайн-документа по arc42 для кейса · 08 Doc-as-Code: диаграмма текстовой нотацией · 09 журнал ADR по системе · 10 мини-проект: мини-HLD (C4 Context+Container + 2 ADR + sequence).
Вопросы (25): уровни C4, что на каждом, ADR-структура/статусы, arc42, Doc-as-Code, выбор диаграммы.

**Модуль 03 — Фреймворк design-интервью**
Теория: формат (45–60 мин, что оценивают); фреймворк 8 шагов (уточнить требования/scope → estimation → API → data model → high-level → deep dive → bottlenecks/scaling/trade-offs → wrap-up); тайм-менеджмент; рубрика по грейдам (junior/mid/senior/staff); типичные ошибки (прыжок в решение, нет уточнений, нет чисел, один вариант без trade-off, молчание); «думать вслух».
Задачи: 01 уточняющие вопросы к «спроектируйте Twitter» · 02 определить scope под тайминг · 03 тайм-бокс 45 минут по этапам · 04 FR/NFR подтвердить до дизайна · 05 ошибки в транскрипте интервью · 06 выбрать компоненты для deep dive + обоснование · 07 3 trade-off вслух · 08 рубрика самооценки по грейдам · 09 восстановление после «застревания» · 10 мини-проект: полный план ответа на интервью (8 этапов тезисно).
Вопросы (25): этапы фреймворка, критерии оценки, тайм-менеджмент, ошибки, грейды, уточнения, think-aloud.

### Батч 2 — Часть 2 «Количественное проектирование» (модули 04–07)

**Модуль 04 — Требования для дизайна (FR/NFR/ограничения)**
Теория: FR в фокусе дизайна (ключевые операции); NFR для дизайна (масштаб, latency p50/p95/p99, доступность, согласованность, durability, безопасность) и их измеримость; constraints (технические/бюджетные/регуляторные/временные); явные assumptions; scope/out-of-scope; как NFR двигают дизайн; read/write-ratio, peak vs average; DAU/MAU; приоритизация под дизайн; отличие от requirements в СА (угол «под нагрузку»).
Задачи: 01 извлечь FR/NFR/ограничения из размытой постановки · 02 размытые NFR → измеримые · 03 scope (в границах/вне) · 04 уточняющие вопросы под дизайн (ось нагрузки) · 05 отделить constraints от requirements · 06 зафиксировать явные допущения · 07 3 NFR → конкретные дизайн-решения · 08 конфликты требований (latency↔consistency) · 09 оценить DAU/MAU/peak из бизнес-контекста · 10 мини-проект: requirements-документ для дизайна.
Вопросы (25): FR/NFR для дизайна, измеримость, constraints vs assumptions, scope, read/write, как NFR двигают дизайн.

**Модуль 05 — Оценка нагрузки (back-of-the-envelope)**
Теория: зачем estimation (отсекает нежизнеспособные дизайны, обосновывает блоки); QPS/RPS; DAU → запросов/день → RPS (день ≈ 86400 ≈ 10^5 c); average vs peak (peak factor 2–10×); read/write-ratio; степени (K/M/B), округление, порядки; fan-out нагрузки; метод (допущения → вычисление → проверка обратным счётом).
Задачи: 01 RPS из DAU · 02 average vs peak с коэффициентом · 03 read/write-ratio и влияние · 04 нагрузка с fan-out (лента) · 05 найти ошибку в расчёте (единицы/порядки) · 06 обратная проверка · 07 нагрузка на пике (Black Friday) · 08 нагрузка по компонентам (кэш vs БД при hit rate) · 09 число серверов (RPS / RPS-на-сервер) · 10 мини-проект: load estimation системы.
Вопросы (25): QPS/RPS, DAU→RPS, peak factor, read/write, порядки величин, метод back-of-the-envelope.

**Модуль 06 — Оценка ёмкости (storage/bandwidth/memory)**
Теория: «числа, которые надо знать» (latency numbers: L1/L2/RAM/SSD/disk/сеть/датацентры); единицы (B/KB/MB/GB/TB/PB, byte=8 бит); storage = размер записи × частота × срок × replication factor; bandwidth (ingress/egress) = данные × RPS; memory для кэша (hot set, правило 80/20); рост данных (1–5 лет); типичные размеры полей (UUID 16B, timestamp 8B, char ASCII 1B).
Задачи: 01 storage за год (URL-shortener) · 02 bandwidth загрузки фото · 03 memory кэша (80/20) · 04 рост на 5 лет с replication · 05 latency numbers — упорядочить/применить · 06 размер записи по полям → объём · 07 найти ошибку в capacity-расчёте · 08 обратная проверка ёмкости · 09 число кэш-серверов под hot set · 10 мини-проект: capacity estimation (storage 5y + bandwidth + memory).
Вопросы (25): единицы, latency numbers, формула storage, bandwidth ingress/egress, memory/80-20, replication factor, рост.

**Модуль 07 — Cost Estimation (TCO, $/запрос)**
Теория: зачем считать деньги (дизайн без $ неполон; senior говорит о стоимости); статьи (compute, storage, bandwidth/egress — дорог!, managed services); $/запрос, $/пользователя, $/мес; TCO (инфра + люди + операционка); cost↔performance (кэш дешевле чтений из БД; CDN дешевле egress); build vs buy (managed vs self-hosted); стоимость как ось дизайна; порядки облачных цен как ориентиры.
Задачи: 01 $/мес compute под RPS · 02 стоимость хранения за год · 03 egress costs и экономия CDN · 04 $/запрос системы · 05 cost trade-off (кэш vs реплики) · 06 build vs buy (managed Kafka) · 07 найти главную статью расходов · 08 TCO двух архитектур · 09 оптимизировать стоимость без потери NFR · 10 мини-проект: cost-model системы ($/мес breakdown + $/запрос + оптимизации).
Вопросы (25): статьи затрат, $/запрос, egress/CDN, TCO, build vs buy, cost как ось дизайна, оптимизация.

### Батч 3 — Часть 3 «Строительные блоки на масштабе (a)» (модули 08–10)

**Модуль 08 — Балансировка нагрузки**
Теория: зачем LB (горизонтальное масштабирование, доступность, скрытие топологии); L4 (transport, TCP/UDP, NAT, быстро) vs L7 (application, HTTP, маршрутизация по пути/хосту, TLS-termination, дороже); алгоритмы (round-robin, weighted RR, least connections, least response time, IP/consistent hash, power-of-two-choices); health checks (active vs passive, пороги, вывод нездоровых); session affinity / sticky sessions (когда нужно, минусы, альтернатива — stateless + внешняя сессия); LB как SPOF → HA-пара (active-passive/active-active, floating IP/VRRP); DNS-based LB и GSLB (geo, anycast); типы (hardware/software — nginx/HAProxy/Envoy / cloud ELB/ALB/NLB); reverse proxy vs LB; connection draining; global vs local LB.
Задачи: 01 выбрать алгоритм под кейс (сравнение) · 02 L4 vs L7 — что и почему · 03 дизайн health-checks (active/passive, пороги, draining) · 04 sticky sessions: нужны ли, stateless-альтернатива · 05 LB как SPOF → HA-схема (диаграмма) · 06 найти ошибки в дизайне с LB · 07 расчёт числа backend'ов с запасом N+1/N+2 на отказ · 08 GSLB / geo-балансировка multi-region · 09 ADR: hardware vs software vs cloud LB · 10 мини-проект: дизайн слоя балансировки системы (от edge до сервиса).
Вопросы (25): зачем LB, L4/L7, алгоритмы, health-checks, sticky vs stateless, SPOF/HA, GSLB/geo, типы LB, draining, reverse proxy.

**Модуль 09 — Кэширование на масштабе**
Теория: зачем кэш (latency, разгрузка БД, стоимость); уровни (client/browser, CDN, reverse-proxy, application in-memory, distributed Redis/Memcached, БД-кэш); паттерны (cache-aside/lazy, read-through, write-through, write-back/behind, write-around); eviction (LRU/LFU/FIFO/TTL); инвалидация («одна из двух трудных задач»): TTL vs событийная, staleness↔freshness; hit/miss rate, 80/20; hot keys; патологии — cache stampede/thundering herd (locking, request coalescing, probabilistic early expiration), cache penetration (null-caching, bloom filter), cache avalanche (jitter TTL); distributed-топология (consistent hashing, near-cache); согласованность кэш↔БД.
Задачи: 01 выбрать паттерн записи (through/back/around) под кейс · 02 рассчитать hit rate и эффект на нагрузку БД/latency · 03 стратегия инвалидации (TTL vs событийная) · 04 eviction под профиль доступа (LRU vs LFU) · 05 решить cache stampede/thundering herd · 06 защита от penetration/avalanche · 07 найти ошибки в дизайне с кэшем (стейл, нет инвалидации) · 08 многоуровневый кэш (client→CDN→app→distributed) · 09 расчёт размера distributed-кэша (memory, число нод) · 10 мини-проект: стратегия кэширования read-heavy системы.
Вопросы (25): зачем/где кэшировать, паттерны записи, eviction, инвалидация, hit rate/80-20, stampede/penetration/avalanche, distributed-топология, согласованность.

**Модуль 10 — CDN и хранение объектов/медиа**
Теория: CDN — зачем (latency через edge-PoP, разгрузка origin, экономия egress, поглощение DDoS); как работает (edge↔origin, pull vs push CDN, cache key, cache-control/TTL, purge/инвалидация, versioned URLs); static vs dynamic, dynamic acceleration/edge compute; object/blob storage (S3-like): immutable-объекты, плоское пространство имён, metadata, HTTP API, durability «11 nines», дёшево — vs block/file; когда blob (медиа, бэкапы, статика, data lake); presigned URLs (direct upload/download, разгрузка app); media pipeline (upload → transcode → store → CDN); storage classes/tiering (hot/cold/archive); multipart/chunked upload; geo-replication.
Задачи: 01 pull vs push CDN под кейс · 02 cache-control/TTL для статики vs динамики · 03 расчёт экономии egress через CDN (offload ratio) · 04 дизайн presigned URL (direct upload в blob) · 05 blob vs block vs file — выбор · 06 media pipeline (upload→transcode→CDN) — диаграмма · 07 storage tiering под профиль доступа + стоимость · 08 найти ошибки (медиа из app, нет CDN) · 09 стратегия purge/инвалидации (versioned URL vs purge) · 10 мини-проект: хранение и доставка медиа (фото/видео-сервис).
Вопросы (25): зачем CDN, pull/push, edge/cache-control/purge, object vs block/file, когда blob, presigned URL, media pipeline, tiering, durability, egress-экономия.

### Батч 4 — Часть 3 «Строительные блоки (b)» (модули 11–13)

**Модуль 11 — Базы данных в дизайне (SQL vs NoSQL)**
Теория: выбор БД как одно из ключевых решений дизайна; реляционные (таблицы, схема, ACID, JOIN, нормализация, сильная согласованность, вертикальный масштаб + read-реплики) — когда транзакции/связи/целостность; NoSQL-семейства (key-value — кэш/сессии; document — гибкие агрегаты; wide-column — write-heavy/time-series по партиционному ключу; graph — обход связей); ACID vs BASE; schema-on-write vs schema-on-read; нормализация vs денормализация под запросы; query-driven моделирование в NoSQL; polyglot persistence; NewSQL (Spanner/CockroachDB — SQL + горизонтальный масштаб); как выбирать (модель данных, паттерны доступа, масштаб, согласованность, транзакции).
Задачи: 01 выбрать тип БД под набор кейсов (финансы/кэш/документы/соцграф/time-series) · 02 SQL vs NoSQL для системы с обоснованием · 03 выбрать NoSQL-семейство под профиль доступа · 04 ACID vs BASE — где что нужно · 05 нормализация vs денормализация (схема) · 06 query-driven моделирование в NoSQL (от паттернов к модели) · 07 найти ошибки в выборе БД (антипаттерны) · 08 polyglot persistence — БД по подсистемам · 09 ADR: SQL vs NoSQL · 10 мини-проект: выбор хранилищ системы (модель + БД на подсистему).
Вопросы (25): зачем выбор БД, реляционные/ACID, NoSQL-семейства, BASE, schema-on-read/write, нормализация/денорм., query-driven, polyglot, NewSQL, критерии выбора.

**Модуль 12 — Репликация и согласованность**
Теория: зачем репликация (доступность, масштаб чтений, гео-близость, durability); топологии — single-leader (primary-replica), multi-leader (multi-DC, конфликты), leaderless (Dynamo-style, кворум); sync vs async (durability vs latency, replication lag); последствия lag — read-your-writes, monotonic reads, consistent prefix; уровни согласованности (strong/eventual/read-your-writes/monotonic); failover (выбор нового лидера, split-brain, потеря данных при async); кворумы (N/W/R, W+R>N → перекрытие); разрешение конфликтов (LWW, vector clocks, CRDT, application merge); согласованность кэша как частный случай.
Задачи: 01 выбрать топологию под кейс · 02 sync vs async trade-off · 03 replication lag: диагноз симптома + решение · 04 обеспечить read-your-writes/monotonic reads · 05 посчитать кворум W/R/N под требование · 06 failover + защита от split-brain · 07 разрешение конфликтов multi-leader (LWW/vector/CRDT) · 08 найти ошибки в дизайне репликации · 09 read-реплики для масштаба чтений (расчёт + ограничения) · 10 мини-проект: схема репликации и согласованности системы.
Вопросы (25): зачем репликация, топологии, sync/async, replication lag, read-your-writes/monotonic, кворумы, failover/split-brain, конфликты LWW/vector/CRDT, durability vs availability.

**Модуль 13 — Шардирование и партиционирование**
Теория: зачем (один узел не вмещает данные/запись); partitioning vs replication (ортогональны, обычно вместе); vertical vs horizontal partitioning; стратегии — range-based (range-запросы, риск hotspot), hash-based (равномерно, нет range), consistent hashing (минимум перемещения), directory/lookup (гибко, но SPOF), geo/entity; выбор shard key (кардинальность, равномерность, соответствие запросам); проблемы — hotspot/celebrity, cross-shard запросы (scatter-gather) и транзакции (2PC, избегать), rebalancing/resharding; mitigation hot key.
Задачи: 01 выбрать стратегию (range/hash/directory) · 02 выбрать shard key + обоснование · 03 диагностировать hot shard/celebrity + решение · 04 range vs hash под конкретные запросы · 05 cross-shard запрос/транзакция — избежать/обработать · 06 resharding-план (почему hash%N плох, consistent hashing) · 07 найти ошибки в шардировании · 08 расчёт числа шардов + реплики на шард · 09 partitioning vs replication — разграничить и скомбинировать (диаграмма) · 10 мини-проект: схема шардирования системы.
Вопросы (25): зачем шардировать, part vs repl, vertical/horizontal, стратегии (range/hash/directory/consistent), выбор shard key, hotspot/celebrity, cross-shard запросы/транзакции, resharding, число шардов, шард как SPOF.

### Батч 5 — Часть 3 «Строительные блоки (c)» (модули 14–17) — завершает Часть 3

**Модуль 14 — Consistent hashing и уникальные ID**
Теория: проблема naive `hash % N` (resharding перетасует всё); hash ring (узлы и ключи на кольце, ключ → первый узел по часовой); добавление/удаление узла переносит лишь долю ключей; неравномерность → virtual nodes (vnodes, веса под разную мощность); где применяется (распределённый кэш, шарды Cassandra/Dynamo, LB); rendezvous hashing (кратко). Уникальные ID: зачем без единой точки (auto-increment = SPOF); требования (уникальность, сортируемость/k-sortable, компактность, без координации); подходы — UUID v4 (уникален, не сортируем, плохой кластерный PK → фрагментация), auto-increment (просто, но SPOF/не для шардов), ticket server/range allocation (Flickr), Snowflake (timestamp+machine+sequence, 64 бита, сортируемый), ULID/KSUID; clock skew как проблема сортируемых ID.
Задачи: 01 показать проблему hash%N + доля перемещения у CH · 02 hash ring: размещение ключей/узлов, добавление узла · 03 virtual nodes (неравномерность + мощность) · 04 выбрать схему ID под кейсы · 05 UUID vs Snowflake vs auto-increment (таблица под требования) · 06 спроектировать Snowflake-ID (раскладка битов под нагрузку) · 07 найти ошибки (hash%N, UUID v4 как PK, auto-inc в шардах) · 08 clock skew и защита · 09 применить CH к задаче (кэш/шарды) + расчёт · 10 мини-проект: распределение данных (CH + генерация ID) системы.
Вопросы (25): проблема hash%N, hash ring, vnodes/веса, rendezvous, где применяется CH; зачем распределённые ID, требования, UUID/auto-inc/ticket/Snowflake/ULID, сортируемость, clock skew.

**Модуль 15 — Очереди и стриминг сообщений**
Теория: зачем асинхронность (decoupling producer/consumer, load leveling/сглаживание пиков, надёжность через retry, масштаб обработки, фон); sync vs async; message queue (RabbitMQ/SQS — сообщение одному консьюмеру, удаляется после ack) vs event streaming/log (Kafka/Kinesis — append-only лог, много консьюмеров, retention, replay); семантика доставки (at-most/at-least/exactly-once и почему exactly-once дорог); идемпотентность консьюмера (обязательна при at-least-once); порядок сообщений (только в партиции, partition key); consumer groups, партиционирование, параллелизм; DLQ, retry с backoff, ядовитые сообщения; pub/sub fan-out; backpressure, мониторинг lag; когда очередь НЕ нужна.
Задачи: 01 queue vs stream под кейс · 02 sync vs async (где развязывать) · 03 семантика доставки + идемпотентность · 04 дизайн идемпотентного консьюмера (дедуп) · 05 партиции + порядок (partition key) · 06 DLQ + retry/backoff · 07 load leveling: расчёт буфера/throughput под пик · 08 pub/sub fan-out · 09 найти ошибки в дизайне с очередями · 10 мини-проект: асинхронная архитектура подсистемы.
Вопросы (25): зачем async/decoupling, load leveling, queue vs stream, доставка at-least/most/exactly, идемпотентность, порядок/партиции, consumer groups, DLQ/retry, pub-sub, когда не нужна.

**Модуль 16 — Rate limiting & throttling**
Теория: зачем (защита от перегрузки/abuse, fair use, контроль стоимости, защита downstream, квоты тарифов, anti-DDoS/brute-force); throttling vs rate limiting; алгоритмы — token bucket (burst, популярный), leaky bucket (сглаживание в равномерный поток), fixed window counter (всплеск ×2 на границе), sliding window log (точный, память), sliding window counter (компромисс); где лимитировать (client/edge-gateway/per-service); ключ лимита (per-user/IP/API-key/глобально, ловушка NAT); распределённый rate limiting (общий счётчик Redis, атомарность, точность vs latency, локальный vs централизованный); ответ при превышении (HTTP 429, Retry-After); soft vs hard limits, graceful degradation.
Задачи: 01 выбрать алгоритм под требование · 02 token vs leaky bucket trade-off · 03 проблема fixed window на границе + sliding window · 04 ключ лимита + ловушка NAT · 05 распределённый rate limiting (Redis) — точность/latency · 06 ответ 429 + Retry-After + backoff клиента · 07 расчёт лимита под ёмкость downstream · 08 найти ошибки · 09 многоуровневый (edge+service) + квоты тарифов · 10 мини-проект: rate limiting публичного API.
Вопросы (25): зачем rate limiting, throttling, token/leaky bucket, fixed/sliding window, граница окна, ключ/NAT, распределённый счётчик, 429/Retry-After, soft/hard, rate limiter как SPOF.

**Модуль 17 — Поиск, автодополнение и геосервисы**
Теория: полнотекстовый поиск — почему не SQL LIKE (не масштабируется, нет ранжирования); inverted index (term → документы); токенизация/нормализация/стемминг/стоп-слова; ранжирование (TF-IDF/BM25 кратко, релевантность); движки (Elasticsearch/OpenSearch, Solr/Lucene); индексация near-real-time, отставание индекса от БД (индекс ≠ источник правды, синхронизация через CDC/очередь). Автодополнение (typeahead): trie (префиксное дерево), топ-K по префиксу, кэш популярных префиксов, latency-критичность. Геосервисы: задача nearby («ближайшее/в радиусе»), проблема 2D→1D индекс, решения — geohash (префикс=близость), quadtree, S2 (Google), H3 (Uber); геоиндексы (PostGIS, Redis GEO, ES geo); граничная проблема geohash (проверять соседние ячейки); шардирование гео по ячейке/региону.
Задачи: 01 почему не LIKE → inverted index · 02 построить inverted index + ранжирование · 03 синхронизация индекса с БД (lag, CDC/очередь) · 04 автодополнение через trie + топ-K · 05 кэш автодополнения (популярные префиксы) · 06 geo-radius: почему линейный перебор плох + выбор подхода · 07 geohash: гранулярность + граничная проблема · 08 шардирование/индексация гео под nearby · 09 найти ошибки в поиске/гео · 10 мини-проект: поиск + автодополнение + гео системы.
Вопросы (25): почему не LIKE, inverted index, токенизация/стемминг, ранжирование TF-IDF/BM25, индексация-lag/индекс не источник правды, trie/typeahead, кэш префиксов, nearby-задача, geohash/quadtree/S2, граничная проблема, шард гео.

### Батч 6 — Часть 4 «Данные и аналитика на масштабе» (модули 18–20)

**Модуль 18 — OLTP vs OLAP, DWH, колоночные БД**
Теория: почему транзакционная и аналитическая нагрузки конфликтуют (разные паттерны, не смешивать на одной БД); OLTP (много мелких транзакций, отдельные строки, низкая latency, нормализация, row-oriented) — это модули 11–13; OLAP (сложные агрегаты по большим объёмам, много строк/мало колонок, пропускная важнее latency, денормализация/звезда); row-oriented vs column-oriented хранение; почему колоночные быстры для аналитики (читают нужные колонки, сжатие похожих значений, векторизация); Data Warehouse (Snowflake/BigQuery/Redshift); star schema (факты+измерения) и snowflake schema; dimensional modeling (fact/dimension tables); разделение OLTP↔OLAP (реплика/ETL/DWH, не гонять аналитику на проде); HTAP (упомянуть).
Задачи: 01 классифицировать нагрузки OLTP/OLAP · 02 row vs column store + почему column быстр · 03 почему не гонять аналитику на OLTP + решение · 04 спроектировать star schema · 05 выбрать хранилище под аналитику (DWH/колоночная) · 06 разделить OLTP/OLAP в архитектуре (диаграмма) · 07 найти ошибки · 08 расчёт объёма DWH / выигрыша сжатия · 09 dimensional modeling (факты vs измерения) · 10 мини-проект: аналитический слой системы.
Вопросы (25): зачем разделять нагрузки, OLTP/OLAP, row vs column, почему column быстр для аналитики, DWH, star/snowflake schema, fact/dimension, разделение OLTP↔OLAP, ETL-задержка, HTAP.

**Модуль 19 — Data Lake, Lakehouse, Data Mesh**
Теория: эволюция DWH→Data Lake→Lakehouse→Data Mesh; Data Lake (сырые данные любого формата дёшево в объектном хранилище, schema-on-read; минус — data swamp без governance); DWH vs Lake (структура/schema-on-write/дорого/BI vs raw/schema-on-read/дёшево/ML); Lakehouse (гибрид: дешёвое хранение озера + транзакции/схема/производительность DWH; table formats — Delta Lake/Iceberg/Hudi с ACID, time travel, schema evolution); Data Mesh (организационный подход: 4 принципа — domain ownership, data as a product, self-serve data platform, federated computational governance; против централизованного монолита при масштабе организации); medallion architecture (bronze/silver/gold); когда что (Lake для raw/ML, DWH/Lakehouse для BI, Mesh для масштаба организации).
Задачи: 01 DWH vs Lake под кейс (BI vs ML/raw) · 02 предотвратить data swamp (governance/каталог/качество) · 03 Lakehouse: какую проблему решает (table format) · 04 schema-on-read vs schema-on-write · 05 применить 4 принципа Mesh к организации · 06 когда Mesh оправдан, а когда overkill · 07 найти ошибки в дизайне платформы · 08 medallion (bronze/silver/gold) под кейс · 09 выбрать платформу под организацию · 10 мини-проект: data-платформа компании.
Вопросы (25): эволюция, Data Lake, data swamp, DWH vs Lake, Lakehouse/table formats, schema-on-read/write, 4 принципа Mesh, Mesh как организационный (не технология), когда Mesh overkill, medallion.

**Модуль 20 — Конвейеры данных (ETL/ELT, streaming, CDC, lineage)**
Теория: зачем конвейеры (перемещать/преобразовывать данные из источников в аналитику); ETL (преобразовать до загрузки, классика DWH) vs ELT (загрузить сырое, преобразовать в хранилище — современно, Lake/облачный DWH, гибко); batch (порциями, высокая пропускная, высокая latency) vs streaming (по событию, низкая latency, real-time); Lambda (batch+speed) и Kappa (всё через стрим) архитектуры; CDC (Change Data Capture — захват изменений из OLTP через лог/binlog, Debezium; альтернатива дорогому полному ETL); оркестрация (Airflow/Dagster, DAG, зависимости, расписание, retry); data quality (валидация, тесты данных, schema enforcement); data lineage (происхождение: откуда и как трансформировано — governance/отладка/compliance); идемпотентность/надёжность (повтор не дублирует), backfill.
Задачи: 01 ETL vs ELT под кейс · 02 batch vs streaming под latency · 03 CDC: захват изменений из OLTP (вместо полного ETL) · 04 оркестрация пайплайна (DAG/зависимости/retry) · 05 идемпотентность/backfill · 06 data quality на входе · 07 data lineage (зачем/как) · 08 найти ошибки в пайплайне · 09 Lambda/Kappa (real-time + историческая аналитика) · 10 мини-проект: data pipeline системы.
Вопросы (25): зачем конвейеры, ETL vs ELT, batch vs streaming, Lambda/Kappa, CDC/binlog/Debezium, оркестрация/DAG, data quality, lineage, идемпотентность/backfill, надёжность.

### Батч 7 — Часть 5 «Распределённые системы для дизайна» (модули 21–23)

**Модуль 21 — CAP/PACELC и выбор согласованности**
Теория: CAP-теорема (Consistency, Availability, Partition tolerance); ключевая тонкость — P (сетевое разделение) неизбежна в распределёнке → реальный выбор CP vs AP при partition; CP (отказываем в обслуживании ради согласованности — банки/инвентарь), AP (обслуживаем ценой временной несогласованности — соцсети/корзины); мифы CAP («выбери 2 из 3» — неверно, P не опциональна; CAP не про обычную работу, только при partition); PACELC (if Partition→C vs A; Else→Latency vs Consistency — даже без partition сильная согласованность стоит latency); спектр согласованности (связь с модулем 12); выбор per-data (деньги CP, лента AP); примеры CP-БД (HBase/Zookeeper/SQL) и AP-БД (Cassandra/DynamoDB/Riak); связь ACID/BASE (модуль 11).
Задачи: 01 классифицировать данные/системы CP/AP · 02 почему «2 из 3» миф (P неизбежна) · 03 CP vs AP при partition для системы + что происходит · 04 PACELC-разбор (P→CA, Else→LC) · 05 спектр согласованности per-data · 06 найти ошибки в рассуждениях о CAP · 07 latency-цена сильной согласованности (PACELC Else) · 08 разные гарантии для разных данных · 09 выбрать БД по CP/AP-профилю · 10 мини-проект: модель согласованности системы.
Вопросы (25): CAP-теорема, почему P неизбежна, CP vs AP, мифы «2 из 3»/«про всё время», PACELC, latency-цена согласованности, спектр, per-data гарантии, примеры CP/AP-БД, связь ACID/BASE.

**Модуль 22 — Координация и консенсус (Raft, leader election, distributed locks)**
Теория: зачем консенсус (узлы согласуют одно значение/решение — кто лидер, зафиксирована ли запись, конфигурация); почему нельзя «на глаз» при сбоях/partition (FLP impossibility — кратко); quorum/majority (решение легитимно при согласии большинства > N/2; почему нечётное число узлов); consensus-алгоритмы (Paxos сложный, Raft понятный: leader election, термы, репликация лога, commit при большинстве); leader election и защита от split-brain (модуль 12); distributed locks (взаимное исключение; держатель упал → нужен lease/TTL; fencing tokens против устаревших держателей); coordination services (Zookeeper/etcd/Consul — готовые примитивы: leader election, locks, config, service discovery); когда консенсус нужен (критичные решения), когда нет (дорог/медленен); логические часы (Lamport/vector — кратко, порядок событий).
Задачи: 01 зачем консенсус (нужен vs не нужен) · 02 кворум/majority: почему нечётное + сколько отказов переживает · 03 Raft на пальцах (leader election + репликация лога) · 04 безопасный leader election (защита от split-brain) · 05 distributed lock + TTL/lease против упавшего держателя · 06 fencing token против устаревшего держателя · 07 выбрать coordination service под задачу · 08 найти ошибки в координации · 09 когда НЕ использовать консенсус (альтернативы) · 10 мини-проект: координационный слой системы.
Вопросы (25): зачем консенсус, FLP, кворум/majority/нечётность, Paxos/Raft, leader election/split-brain, distributed locks/lease/TTL, fencing token, Zookeeper/etcd/Consul, когда не нужен, логические часы.

**Модуль 23 — Надёжность операций (идемпотентность, Saga/Outbox, resilience)**
Теория: в распределёнке вызовы падают/повторяются/выполняются частично → паттерны надёжности; идемпотентность (idempotency key, дедупликация, естественная идемпотентность; обязательна при retry/at-least-once — модуль 15); почему 2PC плох (блокирующий, координатор SPOF, не масштабируется); Saga (последовательность локальных транзакций + компенсирующие действия при сбое; choreography через события vs orchestration координатором; eventual consistency); Outbox pattern (атомарно записать в БД и «событие на отправку» в одной транзакции → отдельный процесс/CDC публикует → решает dual-write problem «записал в БД, но не в Kafka»); resilience patterns (retry с backoff+jitter против retry storm, timeout, circuit breaker против каскадного отказа, bulkhead изоляция, fallback/graceful degradation); связь at-least-once+идемпотентность (15), failover (12), backoff/429 (16).
Задачи: 01 идемпотентная операция (idempotency key) — платёж · 02 почему 2PC плох + альтернатива · 03 Saga (шаги+компенсации; choreography vs orchestration) · 04 Outbox: решить dual-write · 05 retry с backoff+jitter против retry storm · 06 circuit breaker против каскадного отказа · 07 timeout+bulkhead+fallback (комбо) · 08 найти ошибки в надёжности операций · 09 собрать resilience-стек критичной операции · 10 мини-проект: надёжная распределённая операция/процесс.
Вопросы (25): зачем надёжность операций, идемпотентность/key/дедуп, почему 2PC плох, Saga/компенсации/choreography vs orchestration, Outbox/dual-write, retry+backoff+jitter/storm, circuit breaker, timeout/bulkhead/fallback, graceful degradation, связь с 12/15/16.

### Батч 8 — Часть 6 «Надёжность, производительность, масштаб (a)» (модули 24–26)

**Модуль 24 — Масштабируемость и отказоустойчивость**
Теория: vertical (scale up) vs horizontal (scale out) — пределы и trade-off; stateless vs stateful (почему stateless масштабируется легко); shared-nothing; что мешает горизонтальному масштабу (состояние, sticky sessions, единая БД); отказоустойчивость (fault tolerance) — работа при отказе компонентов; redundancy (N+1, N+2, 2N); SPOF — выявление и устранение; failover (active-passive vs active-active); математика доступности («девятки»: 99.9%=8.76ч/год, 99.99%=52.6мин, 99.999%=5.26мин); последовательная доступность = произведение (падает) vs параллельная/redundancy = 1−(1−a)ⁿ (растёт); MTBF/MTTR (availability = MTBF/(MTBF+MTTR)); graceful degradation; blast radius / изоляция отказа, cell-based (кратко); связь с resilience (модуль 23).
Задачи: 01 vertical vs horizontal под кейс · 02 сделать stateful-сервис горизонтально масштабируемым (вынести состояние) · 03 найти SPOF и устранить (redundancy) · 04 расчёт доступности цепочки последовательных компонентов · 05 redundancy N+1/N+2/2N под требование доступности · 06 расчёт «девяток» (downtime/год) под SLA · 07 active-passive vs active-active failover trade-off · 08 найти ошибки (мнимая отказоустойчивость, скрытый SPOF) · 09 blast radius / изоляция отказа (cells) · 10 мини-проект: масштабируемая и отказоустойчивая архитектура системы.
Вопросы (25): vertical/horizontal, scale up/out, stateless/stateful, shared-nothing, SPOF, redundancy N+1/2N, failover active-passive/active-active, девятки, последовательная vs параллельная доступность, MTBF/MTTR, graceful degradation, blast radius/cells.

**Модуль 25 — Multi-region и Disaster Recovery**
Теория: зачем несколько регионов (latency для глобальных юзеров, доступность при отказе целого региона, data residency/регуляторика, DR); AZ vs Region (multi-AZ = HA внутри региона, multi-region = катастрофоустойчивость); топологии multi-region — active-passive (failover) vs active-active (трафик в оба, сложные данные); маршрутизация (GeoDNS, anycast, global LB — модуль 8); проблема данных (репликация через WAN — высокая latency, конфликты при active-active multi-leader — модуль 12); data residency/GDPR; DR-метрики RPO (сколько данных теряем) и RTO (за сколько восстанавливаемся); DR-стратегии по cost/RTO — backup&restore (дёшево, RTO часы), pilot light, warm standby, hot/multi-site active-active (дорого, RTO≈0); механика failover (DNS TTL, health checks, promote replica), failback; тестирование DR (game days); связь с CAP (active-active = AP по WAN).
Задачи: 01 single → multi-region: зачем и что меняется · 02 AZ vs region (multi-AZ HA vs multi-region DR) · 03 active-passive vs active-active multi-region trade-off · 04 определить RPO/RTO по бизнес-критичности · 05 выбрать DR-стратегию под RPO/RTO + cost · 06 данные в multi-region (WAN-репликация, конфликты, residency) · 07 маршрутизация (GeoDNS/anycast) + механика failover · 08 найти ошибки (мнимый/нетестируемый DR, RPO/RTO не заданы) · 09 data residency/GDPR (региональное размещение) · 10 мини-проект: multi-region + DR-план системы.
Вопросы (25): зачем multi-region, AZ vs region, multi-AZ vs multi-region, active-passive/active-active, GeoDNS/anycast, данные WAN/конфликты, data residency, RPO vs RTO, DR-стратегии (backup/pilot/warm/hot), failover/failback, тестирование DR, связь CAP.

**Модуль 26 — Анализ узких мест и оптимизация**
Теория: что такое bottleneck (компонент, ограничивающий пропускную/latency всей системы; теория ограничений — система не быстрее узкого места); как находить (метрики, профилирование, нагрузочное тестирование — модуль 27, мониторинг — модуль 28); типичные узкие места — БД (чаще всего: блокировки, медленные запросы, N+1, нет индексов, исчерпание connection pool), CPU, память, сеть/bandwidth, disk I/O, пулы потоков/соединений, внешние зависимости; latency vs throughput (различие и взаимовлияние); закон Литтла (L=λ·W) для расчёта очередей/пулов; tail latency (p99 важнее среднего; fan-out усиливает хвост — модуль 5); методология (измерить → найти узкое место → устранить → повторить, не оптимизировать вслепую); решения по слою — кэш (9), индексы/денормализация/read-реплики (11–12), шардирование (13), async/очередь (15), CDN (10), горизонтальный масштаб (24); закон Амдала (предел ускорения от параллелизма); premature optimization как антипаттерн; capacity headroom.
Задачи: 01 найти узкое место по симптомам/метрикам · 02 БД как bottleneck (медленные запросы/N+1/индексы/пул) + решения · 03 latency vs throughput: что оптимизировать под кейс · 04 закон Литтла: расчёт размера пула/очереди · 05 tail latency p99: почему хвост важен + эффект fan-out · 06 методология (измерить→устранить→повторить) на кейсе · 07 устранить узкое место подбором блока (кэш/реплики/шард/async) · 08 найти ошибки (оптимизация вслепую, premature optimization, не то узкое место) · 09 закон Амдала / предел масштабирования · 10 мини-проект: анализ узких мест и план оптимизации системы.
Вопросы (25): bottleneck/теория ограничений, как находить, типичные узкие места (БД/CPU/память/сеть/пулы), latency vs throughput, закон Литтла, tail latency/p99/fan-out, методология, решения по слою, закон Амдала, premature optimization, headroom.

### Батч 9 — Часть 6 «Надёжность, производительность, масштаб (b)» (модули 27–28) — завершает Часть 6

**Модуль 27 — Нагрузочное тестирование и Chaos Engineering**
Теория: зачем тестировать под нагрузкой (найти узкие места до прода — модуль 26, проверить capacity-расчёты — модули 5–7, валидировать SLO — модуль 28); виды — load (ожидаемая нагрузка), stress (за пределом, найти точку отказа), spike (резкий всплеск), soak/endurance (долго → утечки/деградация), capacity (сколько держим); методология (baseline → ramp-up → измерение → анализ); метрики (throughput/RPS, latency p50/p95/p99, error rate, утилизация, точка насыщения/knee); открытая vs закрытая модель нагрузки (open — фиксированный rate прибытия; closed — фиксированное число VU ждут ответа; разное поведение под перегрузом); инструменты (k6/Gatling/JMeter/Locust); реалистичная среда/данные; ловушки (тест на проде, нереалистичная нагрузка, coordinated omission). Chaos Engineering: зачем (отказ неизбежен — модуль 24; проверить устойчивость на деле, а не на бумаге); принципы (гипотеза о steady state → внедрить отказ → проверить, что steady state держится → минимизировать blast radius); типы (убить инстанс, сетевая задержка/потеря, отказ зависимости, нехватка ресурсов, отказ зоны/региона — связь DR 25); контроль blast radius (начинать с малого, kill switch, осторожно в проде); game days (плановые учения, в т.ч. DR); Chaos Monkey; культура (учиться, не ломать ради хаоса). Связь: 24/25/26/28.
Задачи: 01 выбрать вид теста под цель (load/stress/spike/soak/capacity) · 02 спроектировать сценарий нагрузочного теста (baseline/ramp-up/метрики/критерии) · 03 интерпретировать результаты (точка насыщения/knee, узкое место) · 04 open vs closed модель нагрузки под кейс · 05 soak test (что ловит — утечки/деградация) · 06 спроектировать chaos-эксперимент по принципам (гипотеза→отказ→проверка→blast radius) · 07 контроль blast radius chaos в проде (малый радиус/kill switch) · 08 найти ошибки (нереалистичный тест, coordinated omission, chaos без гипотезы/наблюдаемости) · 09 game day (план учения, отказ зоны/DR) · 10 мини-проект: план нагрузочного тестирования + chaos системы.
Вопросы (25): зачем нагрузочное тестирование, виды (load/stress/spike/soak/capacity), метрики/точка насыщения, open vs closed модель, coordinated omission, реалистичная среда, зачем chaos, принципы (гипотеза/steady state), типы экспериментов, blast radius/контроль/kill switch, game days, Chaos Monkey/культура, связь с observability.

**Модуль 28 — Observability для дизайна (SLI/SLO, error budget)**
Теория: зачем observability в дизайне (нельзя эксплуатировать то, что не видишь; находить узкие места — 26, подтверждать SLO, диагностировать инциденты); monitoring (известные вопросы/дашборды по заданным метрикам) vs observability (отвечать на новые вопросы о внутреннем состоянии по внешним сигналам, исследовать неизвестное); три столпа — metrics (агрегаты, дёшево, тренды/алерты; RED — Rate/Errors/Duration для сервисов, USE — Utilization/Saturation/Errors для ресурсов), logs (дискретные события, детально, дорого; структурированные), traces (путь запроса через сервисы, span'ы, где latency; correlation/trace ID); SLI (измеримый показатель — доступность, latency p99, error rate), SLO (целевое значение SLI, напр. 99.9% за 30 дней), SLA (контракт с последствиями); error budget (1−SLO = допустимый бюджет ошибок; связывает надёжность и скорость: есть бюджет → катим фичи, исчерпан → стоп на надёжность); как выбрать SLI (важное пользователю, не «всё подряд»); алертинг (на симптомы/SLO-burn, не на причины; alert fatigue; burn rate); ловушка high-cardinality labels; дашборды/on-call. Связь: 26/27/24/23.
Задачи: 01 monitoring vs observability разграничить · 02 определить три столпа под задачу (что metrics/logs/traces) · 03 спроектировать SLI/SLO сервиса (важное пользователю) · 04 рассчитать error budget из SLO + что делать при исчерпании · 05 RED/USE метрики для сервиса/ресурса · 06 распределённый трейсинг (где latency, trace/span) · 07 алертинг на симптомы/SLO-burn, не на причины · 08 найти ошибки (алерт на всё, high-cardinality, SLO не про юзера, путаница SLO/SLA) · 09 разграничить SLI/SLO/SLA/error budget + связь надёжность↔скорость · 10 мини-проект: observability + SLO-стратегия системы.
Вопросы (25): зачем observability, monitoring vs observability, три столпа (metrics/logs/traces), RED/USE, что каждый столп даёт/стоит, SLI, SLO, SLA, разница SLI/SLO/SLA, error budget, error budget связывает надёжность↔скорость, как выбрать SLI, алертинг на симптомы не причины, alert fatigue, burn rate, high-cardinality, trace/span/correlation ID.

---

## Как продолжать (при потере контекста)

1. Прочитай этот файл — найди первый батч со статусом `⬜`/`🟡`.
2. В начале батча допиши **детальный план** его модулей (обязательное содержание `theory.md` + темы 10 задач + тема 25 вопросов) — по образцу `system-analysis-course/PROGRESS.md`, раздел «Детальный план».
3. Сгенерируй модули батча: `theory.md` → `practice/Task01..10.md` → `questions.md`.
4. Проверь по критериям приёмки + скан на кракозябры/NUL/CJK.
5. Обнови статусы (таблицы модулей + батчей + сводка) → git-commit → жди подтверждения на следующий батч.
