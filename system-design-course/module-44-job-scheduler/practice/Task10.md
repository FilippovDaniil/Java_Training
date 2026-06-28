# Задача 10 — Модуль 44 (мини-проект): Полный HLD планировщика задач

## ЗАДАНИЕ
Соберите полный high-level design распределённого планировщика задач по фреймворку design-интервью. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте распределённый планировщик задач: разовые и cron-задачи, надёжное выполнение через воркеры, миллионы задач, отказоустойчивость». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, главное NFR (надёжность/at-least-once).
2. **Estimation** — задачи, неравномерные срабатывания, воркеры.
3. **Компоненты + механизм** — job store, scheduler, queue, workers; поиск due (индекс/timing wheel).
4. **Развязка** — scheduler → очередь → workers (модуль 15).
5. **Надёжность** — at-least-once + идемпотентность + retry/backoff/DLQ (модуль 23).
6. **Отказы** — lease/visibility timeout (воркер), leader election (scheduler, модуль 22).
7. **Recurring/catch-up/приоритеты + узкие места** — next_run, missed; scheduler SPOF/hot time/поиск due.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (job store → scheduler (leader) → queue → workers → статус) + механизм поиска due.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: главное NFR — надёжность/at-least-once; scope зафиксирован.
- [ ] Estimation: задачи/storage, неравномерные срабатывания, число воркеров.
- [ ] Компоненты: job store, scheduler, queue, workers; поиск due (индекс next_run / timing wheel).
- [ ] Развязка: scheduler → очередь → workers (модуль 15), не исполнять в scheduler.
- [ ] Надёжность: at-least-once + идемпотентные задачи + retry/backoff/DLQ (модуль 23).
- [ ] Отказы: lease/visibility timeout + heartbeat (воркер); leader election против двойного запуска (модуль 22).
- [ ] Recurring/catch-up/приоритеты; узкие места (scheduler SPOF/hot time/поиск due); связи 15/23/22/16/11.

## ПОДСКАЗКА
По фреймворку: требования (надёжность) → estimation → компоненты+механизм (job store/scheduler/queue/workers, индекс next_run) → развязка (очередь) → надёжность (at-least-once+идемпотентность+DLQ) → отказы (lease воркера, leader election scheduler) → recurring/catch-up+узкие места. Стержень: развязать планирование и выполнение; at-least-once+идемпотентность; leader election против двойного запуска.

---
<!-- Ваше решение ниже -->
